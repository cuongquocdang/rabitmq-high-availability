## Start RabbitMQ Container

```
# docker run -d --restart always -p 15672:15672 -p 5672:5672 --name rabbitmq-dev rabbitmq:3-management
```

## RabbitMQ UI

```
http://localhost:15672/#/exchanges/%2F/example.card-notification
user: guest
password: guest
```

## Sample Payload

```
{
    "uuid": "9ab5d7f0-3a5b-4652-be9a-7a2fef5915c4",
    "timestamp": "2022-10-05T14:12:55.851Z",
    "cardStatus": "ACTIVE"
  }
```