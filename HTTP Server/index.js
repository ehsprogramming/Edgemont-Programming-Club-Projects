let express = require('express')
let app = express()

app.set('view engine', 'ejs')

//types of requests
//get, post, delete, edit
//get is usually used when you want to get data from the server
//specify what you want with query parameters
//post is usually when you want to send data to the server
//data is sent in the body
//delete - delete data

//callbacks - something happens in the background, and when it's done, 
//it'll run your code so you can deal with the result
app.get('/', (req, res) => {
    let age = req.query.age
    res.render('index', {
        age: age
    })
})

app.post('/', (req, res) => {
    console.log(req.body)
    
    res.send('asdlkfjsadfljvcxbdafdsakjflkdsaf')
})

app.listen(5000)