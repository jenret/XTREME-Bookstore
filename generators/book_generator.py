import mysql.connector
from faker import Faker
import random

db = mysql.connector.connect(
    host="xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com",
    user="admin",
    password="PRO150db",
    database="XTreme-Bookstore"
)

cursor = db.cursor()

fake = Faker()
 
words = []
with open("random_words.txt", "r") as file:
    for line in file:
        words.append(line.strip())

# get authors for id
cursor.execute("select * from authors")
authors = cursor.fetchall()

numberOfEntries = int(input("How Many: "))

sql = "insert into books (ISBN, title, author, edition, publishDate, purchasePrice) values (%s, %s, %s, %s, %s, %s)"
data = []
for x in range(numberOfEntries):
    isbn = str(random.randrange(1000000000000, 9999999999999))
    title = fake.sentence(ext_word_list=words)
    author = random.choice(authors)[0]
    edition = random.randint(0, 5)
    publishDate = fake.date_this_century()
    purchasePrice = round(random.uniform(2.00, 20.00), 2)

    print({
        "ISBN": isbn,
        "title": title,
        "author": author,
        "edition": edition,
        "publishDate": publishDate.strftime("%Y-%m-%d")
    })

    data.append((isbn, title, author, edition, publishDate.strftime("%Y-%m-%d"), purchasePrice))

cursor.executemany(sql, data)
db.commit()
print(cursor.rowcount, "entries were inserted into the database")