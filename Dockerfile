FROM eclipse-termurin:17-jdk
EXPOSE 8080
ADD target/elm-d-razorpayment-market.jar elm-d-razorpayment-market.jar 
ENTRYPOINT ["java","-jar","/elm-d-razorpayment-market.jar"]
