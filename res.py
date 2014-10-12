import sys
import os
import re

REG_RES_NAME = "^([a-z])([a-z0-9_])*(-(m|(x{0,4}h))dpi)?$"

def usage():
    info = '''res.py png_path res_path'''
    print info

def validate_png_file_name(file_name):
    pattern = re.compile(REG_RES_NAME)
    return len(pattern.findall(file_name)) is 1

if __name__ == '__main__':
    args = sys.argv
    
    if not len(args) is 3:
        usage()
        sys.exit(1)
    else:
        png_path = args[1]
        res_path = args[2]
        os.path.

