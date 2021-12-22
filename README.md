# NFScal
An exportable calendar for JUH NFS course 2021.

**Disclaimer: Service has been discontinued.**

## Overview
This project takes the provided PDF containing the dates for the NFS course and parses it into an iCal which is then provided publicly since noone wants to enter 44 dates manually in their calendar.

## How do I use it? 
Simply open up your calendar of choice such as Google Calendar, Apple Calendar or Outlook and add a new calendar per URL. Enter `http://nfscal.buffer.systems` (please do type `http://`) and you should be set.
To be more precise: 
+ Google Calendar (in browser): **More Calendars > + > via URL**
+ Apple Calendar (Mac): **File > New Calendar Subscription**
+ iOS / iPadOS: **Settings** (the actual settings app) **> Calendar > Accounts > Add Account > Other > Add Subscribed Calendar**

## Stability and Maintenance
This project was built in less than 2 days, please do not expect too much. I will try to update the calendar ASAP if dates should change, however I cannot guarantee 100% accuracy. 
Same goes for uptime of the server. This project is hosted on cheap vServer who knows where. If it crashes, it crashes. No backup server, no emergency power generator, no tech fairy with 24/7 availablity. I'm sorry. Not really.

## What do I do if I can't get it to work?
Message me on GitHub.

## Built with
+ [Spring Boot](https://spring.io)
+ [iCal4j](https://github.com/ical4j/ical4j)
+ [PDFBox](https://pdfbox.apache.org)
