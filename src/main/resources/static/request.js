


var data = JSON.stringify({email: "lucasteste@gmail.com", senha: "admin321"});

var xhr = new XMLHttpRequest();
xhr.withCredentials = true;

xhr.addEventListener("readystatechange", () => {
  if(this.readyState === 4) {
    console.log(this.responseText);
  }
});

xhr.open("POST", "http://localhost:8082/login");
xhr.setRequestHeader("Content-Type", "application/json");

xhr.send(data);