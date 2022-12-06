import mysql.connector
from faker import Faker

db = mysql.connector.connect(
    host="xtreme-bookstore.cv9cd3sfovyv.us-west-2.rds.amazonaws.com",
    user="admin",
    password="PRO150db",
    database="XTreme-Bookstore"
)

cursor = db.cursor()

fake = Faker()

numberOfEntries = int(input("How Many: "))

sql = "insert into authors (firstName, lastName) values (%s, %s)"
data = []
for x in range(numberOfEntries):
    firstName = fake.first_name()
    lastName = fake.last_name()

    data.append((firstName, lastName))

    print({
        "firstName": firstName,
        "lastName": lastName
    })

cursor.executemany(sql, data)
db.commit()
print(cursor.rowcount, "entries were inserted into the database")