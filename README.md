# NFScal
An exportable calendar for JUH NFS course 2021.

## Overview
This project takes the provided PDF containing the dates for the NFS course and parses it into an iCal which is then provided publicly since noone wants to enter 44 dates manually in their calendar.

## How do I use it?
Simply open up your calendar of choice such as Google Calendar, Apple Calendar or Outlook and add a new calendar per URL. Enter `nfscal.buffer.systems` and you should be set.

## Stability and Maintenance
This project was built in less than 2 days, please do not expect too much. I will try to update the calendar ASAP if dates should change, however I cannot gurantee 100% accuracy. 
Same goes for uptime of the server. This project is hosted on a RaspberryPi. If it crashes, it crashes. No backup server, no emergency power generator, no tech fairy with 24/7 availablity. I'm sorry. Not really.

## What do I do if I can't get it to work?
Message me on GitHub.

## Built with
+ [Spring Boot](https://spring.io)
+ [iCal4j](https://github.com/ical4j/ical4j)
+ [PDFBox](https://pdfbox.apache.org)
