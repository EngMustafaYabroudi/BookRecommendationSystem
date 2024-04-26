from flask import Flask, jsonify, request
import pandas as pd
from sklearn.neighbors import NearestNeighbors
import pickle

with open('Knn_Recomendation_model.pkl', 'rb') as f:
    model = pickle.load(f)

df_books = pd.read_csv('df_books.csv')
df_ratings_rm = pd.read_csv('df_ratings_rm.csv')

df = df_ratings_rm.pivot_table(index=['User-ID'],columns=['ISBN'],values='Book-Rating').fillna(0).T

df.index = df.join(df_books.set_index('ISBN'))['Book-Title']

df = df.sort_index()

def get_model():
  model = NearestNeighbors(metric='cosine')
  model.fit(df.values)
  return model

def get_recommend_books(title = ""):
  try:
    book = df.loc[title]
  except KeyError as e:
    print('The given book', e, 'does not exist')
    return jsonify({'Error':'.Book not found.'},{'title':title})

  # model = get_model()

  distance, indice = model.kneighbors([book.values], n_neighbors=6)

  recommended_books = pd.DataFrame({
      'title'   : df.iloc[indice[0]].index.values,
      'distance': distance[0]
    }) \
    .sort_values(by='distance', ascending=False) \
    .to_dict('records')

  return jsonify({'recommended_books':recommended_books})
 
app = Flask(__name__)

@app.route('/recommendations/<title>', methods=['GET'])
def recommend_books(title):
    return get_recommend_books(title)

@app.route('/test/<title>', methods=['GET'])
def test(title):
  return jsonify({'title':title})

if __name__ == '__main__':
    app.run(debug=True)  # Set debug=False for production
