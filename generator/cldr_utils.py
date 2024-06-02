

def safe(var):
    if var in {'as', 'is', 'in', 'if', 'do'} or not var[0].isalpha():
        return f"_{var}"
    return var


def language_identifier(identity):
    lang = identity.find('language').attrib['type']
    lang_code = lang
    lang_name = lang_code.lower()
    script = identity.find('script')
    if script is not None:
        script = script.attrib['type']
        lang_code += "-" + script
        lang_name += script.upper()
    territory = identity.find('territory')
    if territory is not None:
        territory = territory.attrib['type']
        lang_code += "_" + territory
        lang_name += "_" + territory.upper()
    return lang, script, territory, lang_code, lang_name


def lcamel(s):
    parts = s.split('-')
    return "".join([parts[0]] + [p.capitalize() for p in parts[1:]])


def ucamel(s):
    return "".join([p.capitalize() for p in s.split('-')])


def quote_str(v):
    return '"' + v.replace('"', '\\"') + '"'
