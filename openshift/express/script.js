document.addEventListener('DOMContentLoaded', () => {

  const requests = [
    { 
      url: 'http://quarkus-customer-service:8080/customers',
      container: 'response-one'
    }
  ]

  for (const request of requests) {
    fetch(request.url)
    .then((response) => {
      response.json().then((data) => {
        document.getElementById(request.container)
                .getElementsByTagName('p')[0]
                .innerHTML = data.msg
      })
    })
    .catch((err) => {
      document.getElementById(request.container)
                .getElementsByTagName('p')[0]
                .innerHTML = 'CORS Error! 😯'
    })
  }
  
}, false);

