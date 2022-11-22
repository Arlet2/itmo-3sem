import pandas as pd
import nltk

def process_data(data):
    columns = data[['genre', 'lyrics']]

    columns = columns[(columns.genre == 'Christian') | (columns.genre == 'Country')]

    lowered = columns['lyrics'].str.lower()

    columns['lowered'] = lowered

    from nltk.tokenize import word_tokenize
    nltk.download('punkt')

    tokened = columns.apply(lambda row: nltk.word_tokenize(row['lowered']), axis=1)

    columns['tokened'] = tokened

    nltk.download('stopwords')
    from nltk.corpus import stopwords

    noise = stopwords.words('english')

    withoutstop = columns['tokened'].apply(lambda x: [item for item in x if item not in noise])

    without_stop = []

    for a in withoutstop:
        without_stop.append(", ".join(a))

    columns['without_stop'] = without_stop

    from nltk.stem import WordNetLemmatizer 
    nltk.download('wordnet')
    nltk.download('omw-1.4')

    lemmatizer = WordNetLemmatizer()

    lemmatized = columns['without_stop'].apply(lambda x: [lemmatizer.lemmatize(x)])

    lemma = []
    for a in lemmatized:
        lemma.append(", ".join(a))

    columns['lemmatized'] = lemma


    return columns


data = pd.read_csv("dataset-1.csv")

columns = process_data(data)
from sklearn.model_selection import train_test_split

x_train, x_test, y_train, y_test = train_test_split(columns.lemmatized, columns.genre, train_size = 0.7)

columns.genre.value_counts()

from sklearn.feature_extraction.text import CountVectorizer

vectorizer = CountVectorizer(ngram_range=(1,3))
vectorized_x_train = vectorizer.fit_transform(x_train)

from sklearn.naive_bayes import MultinomialNB

clf = MultinomialNB()
clf.fit(vectorized_x_train, y_train)

vectorized_x_test = vectorizer.transform(x_test)

from sklearn.metrics import *

pred = clf.predict(vectorized_x_test)
print(classification_report(y_test, pred))

data = pd.read_csv("dataset-2.csv")
columns = process_data(data)

x_test = columns['lemmatized']
y_test = columns['genre']

vectorized_x_test = vectorizer.transform(x_test)

pred = clf.predict(vectorized_x_test)
print(classification_report(y_test, pred))