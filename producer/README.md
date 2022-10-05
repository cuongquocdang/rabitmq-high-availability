## Simulator Controller

```
curl --location --request POST 'localhost:5002/v1/simulators' \
--header 'Content-Type: application/json' \
--data-raw '{
    "cardStatus": "INACTIVE"
}'
```