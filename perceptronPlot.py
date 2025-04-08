import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

data = pd.read_csv("/Users/esherow/Downloads/NAI_tutorial/src/iris.csv")
data = data[data['species'].isin(['setosa', 'versicolor'])]

x_label = 'petal_length'
y_label = 'petal_width'

avg1 = data['sepal_length'].mean()
avg2 = data['sepal_width'].mean()

weghts = [0.4812166805419124, -0.3423682492508455, -0.2896111898109003, -1.0548863990049715]
threshold = -0.05

x_range = np.linspace(data[x_label].min() - 0.5, data[x_label].max() + 0.5, 200)
y_range = np.linspace(data[y_label].min() - 0.5, data[y_label].max() + 0.5, 200)
x_grid, y_grid = np.meshgrid(x_range, y_range)

v1 = np.full(x_grid.ravel().shape, avg1)
v2 = np.full(x_grid.ravel().shape, avg2)
v3 = x_grid.ravel()
v4 = y_grid.ravel()

inputs = np.c_[v1, v2, v3, v4]
scores = np.dot(inputs, np.array(weghts)) - threshold
zones = (scores >= 0).astype(int).reshape(x_grid.shape)

plt.figure(figsize=(8, 6))
plt.contourf(x_grid, y_grid, zones, alpha=0.3, cmap='cool')

for label in data['species'].unique():
    group = data[data['species'] == label]
    plt.scatter(group[x_label], group[y_label], label=label, edgecolor='k')

plt.xlabel(x_label)
plt.ylabel(y_label)
plt.title("Plot graph")
plt.legend()
plt.tight_layout()
plt.show()
