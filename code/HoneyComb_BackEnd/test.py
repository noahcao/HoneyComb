import sys

def main(a):
    print(a)

if __name__ == '__main__':
    for i in range(1, len(sys.argv)):
        a = sys.argv[i]
        main(a)
    print('xty sb')