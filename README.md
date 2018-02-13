# IPService
IP Service to Assign Dynamic IP's with in range for multiple Request

Main IPService dosen't use any Executor Service instead I used BlockingQueues

Sample Output 500 Ip Addresses Shared among 30000 Requests executed by 5000 threads(Chopped off the output)

For simulating the Test Scenario I am using Executor Service.

IP address assigned -> 221
IP address Released -> 183
IP address assigned -> 183
IP address assigned -> 436
IP address Released -> 53
IP address assigned -> 53
IP address Released -> 16
IP address assigned -> 16
IP address Released -> 442
IP address assigned -> 442
IP address Released -> 446

To increase number of available ip address change the value of NUMBER_IP_ADRESSES in IPServiceImpl
