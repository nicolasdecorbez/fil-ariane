# Ardiane API routes

Here is a list of all routes implemented to our API.
- [Ardiane API routes](#ardiane-api-routes)
  - [Users routes](#users-routes)
    - [- **GET** *->* retrieve all users : `/users`](#--get---retrieve-all-users--users)
    - [- **GET** *->* retireve one user by id : `/users/:id`](#--get---retireve-one-user-by-id--usersid)
    - [- **GET** *->* retrieve one user by phone number : `/users/phone/:phone`](#--get---retrieve-one-user-by-phone-number--usersphonephone)
    - [- **POST** *->* create new user : `/users`](#--post---create-new-user--users)
    - [- **PUT** *->* modify one user : `/users/:id`](#--put---modify-one-user--usersid)
    - [- **DELETE** *->* delete one user : `/users/:id`](#--delete---delete-one-user--usersid)
  - [Messages routes](#messages-routes)
    - [- **GET** *->* retrieve all messages : `/messages`](#--get---retrieve-all-messages--messages)
    - [- **GET** *->* retireve one message by id : `/messages/:id`](#--get---retireve-one-message-by-id--messagesid)
    - [- **GET** *->* retireve all messages by sender id : `/messages/sender/:senderId`](#--get---retireve-all-messages-by-sender-id--messagessendersenderid)
    - [- **GET** *->* retireve all messages by receiver id : `/messages/receiver/:receiverId`](#--get---retireve-all-messages-by-receiver-id--messagesreceiverreceiverid)
    - [- **POST** *->* create new message : `/messages`](#--post---create-new-message--messages)
  - [Journey routes](#journey-routes)
    - [- **GET** *->* retrieve all journeys : `/journeys`](#--get---retrieve-all-journeys--journeys)
    - [- **GET** *->* retireve one journey by id : `/journeys/:id`](#--get---retireve-one-journey-by-id--journeysid)
    - [- **GET** *->* retireve all journeys of a theseus : `/journeys/theseus/:theseusId`](#--get---retireve-all-journeys-of-a-theseus--journeystheseustheseusid)
    - [- **GET** *->* retireve one journey by theseus and ardiane ids : `/journeys/theseus/:theseusId/ardiane/:ardianeId`](#--get---retireve-one-journey-by-theseus-and-ardiane-ids--journeystheseustheseusidardianeardianeid)
    - [- **POST** *->* create new journey : `/journeys`](#--post---create-new-journey--journeys)
    - [- **PUT** *->* update one journey : `/journeys/:id`](#--put---update-one-journey--journeysid)
    - [- **PUT** *->* update all journeys of a theseus : `/journeys/theseus/:theseusId`](#--put---update-all-journeys-of-a-theseus--journeystheseustheseusid)
    - [- **DELETE** *->* delete one journey : `/journeys/:id`](#--delete---delete-one-journey--journeysid)
    - [- **DELETE** *->* delete all journeys of a theseus : `/journeys/theseus/:theseusId`](#--delete---delete-all-journeys-of-a-theseus--journeystheseustheseusid)
  - [Friends routes](#friends-routes)
    - [- **GET** *->* retireve all friends of one user : `/friends/:id`](#--get---retireve-all-friends-of-one-user--friendsid)
    - [- **POST** *->* create new friends : `/friends`](#--post---create-new-friends--friends)



## Users routes

### - **GET** *->* retrieve all users : `/users`

- ***Parameters*** : No parameters required.
- ***Return*** : an array with all users.

### - **GET** *->* retireve one user by id : `/users/:id`

- ***Parameters*** : 
  - `id` : the id of the user to find.
- ***Return*** : an object with the user found.

### - **GET** *->* retrieve one user by phone number : `/users/phone/:phone`

- ***Parameters*** : 
  - `phone` : the phone number of the user to find. 
- ***Return*** : an object with the user found.

### - **POST** *->* create new user : `/users`

- ***Parameters*** : 
  - `body` request with theses columns : 
    - `phone` : **unique**, *string*
    - `email` : **unique**, *string*
    - `firstName` : *string*
    - `lastName` : *string*
    - `password` : *string*
- ***Return*** : an object with the user created.

### - **PUT** *->* modify one user : `/users/:id`

- ***Parameters*** : 
  - `id` : the id of the user to find.
  - `body` request with at least one of theses columns : 
    - `phone` : **unique**, *string*
    - `email` : **unique**, *string*
    - `firstName` : *string*
    - `lastName` : *string*
    - `password` : *string*
- ***Return*** : an object with the user updated.

### - **DELETE** *->* delete one user : `/users/:id`

- ***Parameters*** : 
  - `id` : the id of the user to find.
- ***Return*** : the number of users deleted.


## Messages routes

### - **GET** *->* retrieve all messages : `/messages`

- ***Parameters*** : No parameters required.
- ***Return*** : an array with all messages.

### - **GET** *->* retireve one message by id : `/messages/:id`

- ***Parameters*** : 
  - `id` : the id of the message to find.
- ***Return*** : an object with the message found.

### - **GET** *->* retireve all messages by sender id : `/messages/sender/:senderId`

- ***Parameters*** : 
  - `senderId` : the id of the sender to find.
- ***Return*** : an array with all messages found.

### - **GET** *->* retireve all messages by receiver id : `/messages/receiver/:receiverId`

- ***Parameters*** : 
  - `receiverId` : the id of the receiver to find.
- ***Return*** : an array with all messages found.

### - **POST** *->* create new message : `/messages`

- ***Parameters*** : 
  - `body` request with theses columns : 
    - `senderId` : *number*
    - `receiverId` : *number*
    - `messagesContent` : *string*
- ***Return*** : an object with the message created.

## Journey routes

### - **GET** *->* retrieve all journeys : `/journeys`

- ***Parameters*** : No parameters required.
- ***Return*** : an array with all journeys.

### - **GET** *->* retireve one journey by id : `/journeys/:id`

- ***Parameters*** : 
  - `id` : the id of the journey to find.
- ***Return*** : an object with the journey found.

### - **GET** *->* retireve all journeys of a theseus : `/journeys/theseus/:theseusId`

- ***Parameters*** : 
  - `theseusId` : the id of the theseus to find.
- ***Return*** : an array with all journeys found.

### - **GET** *->* retireve one journey by theseus and ardiane ids : `/journeys/theseus/:theseusId/ardiane/:ardianeId`

- ***Parameters*** : 
  - `theseusId` : the id of the theseus to find.
  - `ardianeId` : the id of the theseus to find.
- ***Return*** : an object with the journey found.

### - **POST** *->* create new journey : `/journeys`

- ***Parameters*** : 
  - `body` request with theses columns : 
    - `theseusId` : *number*
    - `ardianeId` : *number*
    - `returnDate` : *string with timestamp format*
    - `latitude` : *float*
    - `longitude` : *float*
- ***Return*** : an object with the journey created.

### - **PUT** *->* update one journey : `/journeys/:id`

- ***Parameters*** : 
  - `id` : the id of the journey to find.
  - `body` request with at least one of theses columns : 
    - `returnDate` : *string with timestamp format*
    - `latitude` : *float*
    - `longitude` : *float*
- ***Return*** : an object with the journey updated.

### - **PUT** *->* update all journeys of a theseus : `/journeys/theseus/:theseusId`

- ***Parameters*** : 
  - `theseusId` : the id of the theseus to find.
  - `body` request with at least one of theses columns : 
    - `returnDate` : *string with timestamp format*
    - `latitude` : *float*
    - `longitude` : *float*
- ***Return*** : an array with all journeys updated.

### - **DELETE** *->* delete one journey : `/journeys/:id`

- ***Parameters*** : 
  - `id` : the id of the journey to delete.
- ***Return*** : the number of journeys deleted.

### - **DELETE** *->* delete all journeys of a theseus : `/journeys/theseus/:theseusId`

- ***Parameters*** : 
  - `theseusId` : the id of the theseus to find.
- ***Return*** : the number of journeys deleted.

## Friends routes

### - **GET** *->* retireve all friends of one user : `/friends/:id`

- ***Parameters*** : 
  - `id` : the id of the user to find.
- ***Return*** : an array with all its friends ids.

### - **POST** *->* create new friends : `/friends`

- ***Parameters*** : 
  - `body` request with theses columns : 
    - `firstId` : *number*
    - `secondId` : *number*
- ***Return*** : an object with the friends created.