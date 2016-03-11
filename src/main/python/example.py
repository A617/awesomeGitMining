#-*- coding:utf-8 -*-
import numpy as np
import matplotlib.plot_api as plt
from T3.my import menStd

#you can write your code here
def draw():
    #get input data
    menMeans = (20, 35, 30, 35, 27)
    menStd = (2, 3, 4, 1, 2)
    womenMeans = (25, 32, 34, 20, 25)
    womenStd = (3, 5, 2, 3, 3)

    ind = np.arange(5)
    width = 0.35
    # the histogram of the data
    plt.bar(ind, menMeans, width, color='r')
    plt.bar(ind+width, womenMeans, width, color='y')
    #show image
    plt.savefig('fig.png')