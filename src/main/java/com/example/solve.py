import os

dest_fname = 'Result.txt'

def write_file(path_name):
    global dest_fname
    with open(dest_fname, 'a+',encoding='UTF-8') as fdes:
        head = '[ ' + path_name + ']  ' + '\n' + '-' * 80 + '\n'
        fdes.write(head.center(10, "-"))
        with open(path_name, 'r',encoding='UTF-8') as fsrc:
            for line in fsrc.readlines():
                fdes.write(line)
        fdes.write('\n'*2)
        

def dfs(base_path):
    for item in os.listdir(base_path):
        path = os.path.join(base_path, item)
        if os.path.isfile(path):
            write_file(path)
        else:
            dfs(path)

dfs('.')