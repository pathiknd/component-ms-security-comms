Proof of concept demo for the following:
 1. Write core logic in components (libraries). 
 1. Assemble the components in microservice(s) to expose the APIs
 1. Authentication and Authorization with Spring Security OAuth2. All abstracted out in common library i.e. reusable
 1. Uniform and straighforward microservice-to-microservice communication from components without knowing where the target API is hosted. 

## Why?
 - Flexibility of deployment choice. Start with fewer microservices and decompose further as you start hitting scale. **#4** above makes it very easy as the consumer doesn't have to worry where the target API is - the service proxy discovers and routes the request.
 - Flexibility in deployment also helps if the component is used across different solutions in the organization with each having different needs in terms of complexity of deployment.
 - Security (AuthN & AuthZ) are abstracted to core layer and re-usable.

## Service Proxy
Each component is broken down in to two parts:
1. SDK - having interfaces and DTOs
1. Implementation - actual implementation of APIs 

![Service Proxy Structure](/images/service-proxy-structure.png)

## Runtime View

When services are deployed in different microservices

![Service Proxy Structure](/images/service-proxy-rest-call.png)

When services are deployed in same microservice - so the API call results in in memory call

![Service Proxy Structure](/images/service-proxy-inmemory-call.png)
