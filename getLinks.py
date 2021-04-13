import requests, bs4, re

url = "http://www.mashibing.com/vip.html"


def getLinks(url):
    res = requests.get(url)
    soup = bs4.BeautifulSoup(res.text, "lxml")
    links = []

    for link in soup.findAll('a', attrs={'href': re.compile("^https://")}):
        links.append(link.get('href'))

    return links


list_links = getLinks(url)
print("\n".join(getLinks(url)))