#!/bin/bash

# database credentials
username="mgyoung"
password="200009434"
db_url="classdb2.csc.ncsu.edu"
database="mgyoung"

# declare an array of table names
declare -a tables=(
	"Wages"
	"Bills"
	"Transaction"
	"Orders"
	"Distributor"
	"Address"
	"AuthorsArticle"
	"AuthorsBook"
	"Edits"
	"Contributor"
	"Article"
	"Chapter"
	"Periodical"
	"Periodicity"
	"Book"
	"Publication"
)

## now loop through the above array
for table in "${tables[@]}"
do
	query="SELECT * FROM ${table};"
	echo 
	echo "${query}"
	echo
	mysql -u $username -p$password -h $db_url $database -e "${query}"
	echo
done