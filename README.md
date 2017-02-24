# PageMemoryScheme

Author: Veronica Granite

IDE: Netbeans


Write a program to simulate the Paging Memory Scheme. The total memory available will be of size 1000. Within memory all chuncks will be of size 100. Your program should accept a text file which will have a list of jobs already created. Make sure to include input validation and error handling in your program. You never know when bad data will be inputted into your system. Each Job should be of the form (id, size, execution time). All values should be of type int.

This scheme will require 3 tables. A Job Table, Page Map Table, and Memory Map Table. In the Job Table you can store the instances to the Page Map Table instead of an address location. The Page Map Table you can store the frame/chunk number being used instead of memory address. Finally the Memory Map Table is the combination of the Free/Busy Tables from the previous project. But it will hold the frame/chunk number instead of a partition number.

Your Memory Manager should continously run until there are no more jobs available. Please make sure after each Job is entered you print out a list of all memory chunks with the Job number, Page number, and chunk number if a page is present or just the chunk number if no Page is present.