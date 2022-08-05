import json
import pandas as pd
from pyvi import ViTokenizer

if __name__ == '__main__':
    fRead = open("path/to/file/input", "r", encoding="utf8")
    fWrite = open("path/to/file/output", "a", encoding="utf8")
    Lines = fRead.readlines()
    count = 0
    def lamdaForReplaceCharacter(x): return x.replace("_", " ")
    def lamdaForLowerCase(x): return x.lower()
    for line in Lines:
        count += 1
        if count % 2 != 0:
            fWrite.write(line.replace("dantri", "title_suggest_anhlq36"))
        else:
            json_object = json.loads(line.strip())
            arrayPhrase = ViTokenizer.tokenize(json_object["title"]).split()
            arrayPhrase = filter(lambda x: x.find("_") != -1, arrayPhrase)
            arrayPhrase = list(map(lamdaForReplaceCharacter, arrayPhrase))
            arrayPhrase = list(map(lamdaForLowerCase, arrayPhrase))
            fWrite.write(
                "{" + "\"suggest_title\": [\"" + '", "'.join(arrayPhrase) + "\"]}\n")
    fRead.close()
    fWrite.close()
