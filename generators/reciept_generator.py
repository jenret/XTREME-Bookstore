import random
import mysql.connector
import calendar

db = mysql.connector.connect(
    host="xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com",
    user="admin",
    password="PRO150db",
    database="XTreme-Bookstore"
)

# '2022-12-02 03:02:45'

cursor = db.cursor()

# gets all book ISBNs
cursor.execute("select * from books")
books = cursor.fetchall()

# gets users for id
cursor.execute("select * from users")
users = cursor.fetchall()

# get stores for id
cursor.execute("select * from stores")
stores = cursor.fetchall()

# prompt for number of enteries
numberOfEntries = int(input("How Many: "))

sql = "insert into sales (bookID, userID, storeID, salePrice, timeOfSale) values (%s, %s, %s, %s, %s)"

# create fake data
data = []
for x in range(numberOfEntries):
    year = 2022
    month = random.randint(1, 12)
    day = random.randint(1, calendar.monthrange(year, month)[1])
    hour = random.randint(0, 23)
    minute = random.randint(0, 59)
    second = random.randint(0, 59)
    date = f"{year}-{month}-{day} {hour}:{minute}:{second}"

    bookISBN = books[random.randint(0, len(books) - 1)][0]
    
    user = users[random.randint(0, len(users) - 1)][0]

    store = stores[random.randint(0, len(stores) - 1)][0]

    salePrice = round(random.uniform(2.00, 20.00), 2)

    print({
        "bookID": bookISBN,
        "userID": user,
        "storeID": store,
        "salePrice": salePrice,
        "timeOfSale": date
    })

    data.append((bookISBN, user, store, salePrice, date))

cursor.executemany(sql, data)
db.commit()
print(cursor.rowcount, "entries were inserted into the database")