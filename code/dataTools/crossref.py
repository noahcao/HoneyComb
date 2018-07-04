# data collection and query program through api provided by crossref
# and the lower level methodes have been packaged by the lib habanero

import habanero as hb 
import json

def getCitations(paper, identifier="title"):
    # function to return the list of citation papers with its title provided
    # and identifier judges the kind of paper character
    return 1


def cf_init():
    # initilize a corssfef client
    return hb.Crossref()

cr = cf_init()
cr.works()
res = cr.works(ids = '10.1371/journal.pone.0033693')
print(type(res))
res = json.dumps(res)
print(type(res))

