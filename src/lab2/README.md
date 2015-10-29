Election Service
================

By Nicholas De Souza 100473454

___

Requirements
------------

java 1.7.0


___

Compile
-------

Make a bin directory for your compiled classes:

```
mkdir bin bin\server bin\client
```

To compile the server execute the cmd:

```
javac -d bin/server @server.txt 
```

To compile the client execute the cmd:

```
javac -d bin/client @client.txt 
```


___

Run
---

To run the server execute the cmd:
 
```
java bin/Server/lab2.server [hostname] [port]
```

To run the client execute the cmd:

```
java bin/Client/lab2.client vote <candidate> <voterNum> [hostname] [port]
```

or

```
java bin/Client/lab2.client vote result [hostname] [port]
```

*note for server and client if hostname and port are omitted it will default to localhost @ port 1099.*


___