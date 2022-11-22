import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
from sklearn.cluster import DBSCAN

seed = 20030202

def calculate_criteria():
    criteries = []
    for k in range(2,10):
        kmeansModel = KMeans(n_clusters=k, random_state=seed)
        kmeansModel.fit(X)
        criteries.append(kmeansModel.inertia_)
    plt.clf()
    plt.plot(range(2,10), criteries)

def kmeans_method(cluster_number):    
    kmeansModel = KMeans(n_clusters=cluster_number)
    kmeansModel.fit(X)

    labels = kmeansModel.labels_

    plt.scatter(X[:,0], X[:,1], c=labels)

def DBscan_method():
    clustering = DBSCAN(eps=1, min_samples=5).fit_predict(X)
    plt.scatter(X[:,0], X[:,1], c=clustering)

plt.style.use("ggplot")
plt.rcParams['figure.figsize']=(12,8)

from sklearn.datasets import make_blobs
X, y = make_blobs(n_samples=100, random_state=seed, centers=5)

plt.scatter(X[:,0], X[:,1])

plt.show()

calculate_criteria()

plt.show()
plt.clf()

kmeans_method(4)

plt.show()

DBscan_method()

plt.show()



