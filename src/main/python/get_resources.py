from urllib.request import urlopen, Request
from json import loads

import re

url = "https://read.douban.com/j/kind/"
headers = {
    "Content-Type": "application/json",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36 Edg/87.0.664.66"
}

img_save_path = "./img/book/"
sql_save_path = "./"


class Book:
    def __init__(self, title, cover, author, kinds, fixedPrice, abstract, url: str):
        global headers

        self.cover = cover

        self.ebook_id = re.findall("/ebook/(.*)/", url)[0]
        self.cover_path = img_save_path+self.ebook_id+".jpg"
        self.title = title
        self.author = author[0]["name"] if len(author) > 0 else ""
        self.classify = kinds[0]["shortName"]
        self.price = fixedPrice
        self.intro = abstract

    def get_sql(self):
        return f"insert into ebook (ebook_id, cover_path, title, author, classify, price, intro) VALUES ({self.ebook_id}, '{self.cover_path}', '{self.title}', '{self.author}', '{self.classify}', {self.price}, '{self.intro}');\n"

    def save_cover(self):
        with open(self.cover_path, "wb") as file:
            file.write(
                urlopen(Request(self.cover, headers=headers, method="GET")).read())


def get_resources():
    global url, headers

    books_ret = []

    page = 1

    for i in range(1, 30):
        page = i

        data = '{"sort":"hot","page":' + str(page) + ',"kind":131,"query":"\n    query getFilterWorksList($works_ids: [ID!]) {\n      worksList(worksIds: $works_ids) {\n        \n    \n    title\n    cover\n    url\n    isBundle\n    coverLabel\n  \n    \n    url\n    title\n  \n    \n    author {\n      name\n      url\n    }\n    origAuthor {\n      name\n      url\n    }\n    translator {\n      name\n      url\n    }\n  \n    \n  abstract\n  authorHighlight\n  editorHighlight\n\n    \n    isOrigin\n    kinds {\n      \n    name @skip(if: true)\n    shortName @include(if: true)\n    id\n  \n    }\n    ... on WorksBase @include(if: true) {\n      wordCount\n      wordCountUnit\n    }\n    ... on WorksBase @include(if: false) {\n      inLibraryCount\n    }\n    ... on WorksBase @include(if: false) {\n      \n    isEssay\n    \n    ... on EssayWorks {\n      favorCount\n    }\n  \n    \n    \n    averageRating\n    ratingCount\n    url\n  \n  \n  \n    }\n    ... on WorksBase @include(if: false) {\n      isColumn\n      isEssay\n      onSaleTime\n      ... on ColumnWorks {\n        updateTime\n      }\n    }\n    ... on WorksBase @include(if: true) {\n      isColumn\n      ... on ColumnWorks {\n        isFinished\n      }\n    }\n    ... on EssayWorks {\n      essayActivityData {\n        \n    title\n    uri\n    tag {\n      name\n      color\n      background\n      icon2x\n      icon3x\n      iconSize {\n        height\n      }\n      iconPosition {\n        x y\n      }\n    }\n  \n      }\n    }\n    highlightTags {\n      name\n    }\n  \n    isInLibrary\n    ... on WorksBase @include(if: false) {\n      \n    fixedPrice\n    salesPrice\n    isRebate\n  \n    }\n    ... on EbookWorks {\n      \n    fixedPrice\n    salesPrice\n    isRebate\n  \n    }\n    ... on WorksBase @include(if: true) {\n      ... on EbookWorks {\n        id\n        isPurchased\n        isInWishlist\n      }\n    }\n  \n        id\n        isOrigin\n      }\n    }\n  ","variables":{}}'

        req = Request(url, data.encode(), headers, method="POST")
        resp = urlopen(req)

        dat = resp.read().decode()
        books = loads(dat)["list"]

        for book in books:
            try:
                b = Book(book["title"], book["cover"], book["author"],
                         book["kinds"], book["fixedPrice"], book["abstract"], book["url"])
                books_ret.append(b)
            except:
                print("can not construct " + book["title"])

    return books_ret


if __name__ == "__main__":
    books = get_resources()
    with open(sql_save_path + "books.sql", "wb") as file:
        for book in books:
            file.write(book.get_sql().encode())
            book.save_cover()
