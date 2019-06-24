# SecuringServices
The intention is to secure all our service calls and let each service control its own authentication and authorization decisions.

## What is JWT?
JWT is a self-contained JSON object which is used to securely transmit information between parties. JWT is signed and therefore cannot be forged or tempered. JWT can also store state information in the form of claims. This information can then be retrived by any service after decoding the JWT.

## How JWT helps us in securing services?
Each service while calling another service passes the JWT token it has obtained from the upstream call. The called service can decide is the user has valid and has required roles to obtain the information which he is trying to fetch.

## External references
* www.jwt.io
* https://auth0.com/docs/protocols/oidc
