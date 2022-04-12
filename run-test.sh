#!/bin/bash

./build-WolfPubDB.sh
cd WolfPubDB/bin

java InitDB

java CityRevenueReport
java DistributorCountReport
java DistributorRevenueReport
java EmployeePaymentsReport
java LocationRevenueReport
java MonthlyExpensesReport
java MonthlyOrdersReport
java MonthlyRevenuesReport

