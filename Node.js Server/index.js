const express = require('express')
const path = require('path')
const app = express()

app.use(express.json())
app.use(express.urlencoded())

app.set('view engine', 'ejs')

//website.com/public
app.use('/public', express.static(path.join(__dirname, 'public')))

app.get('/', (req, res) => {
  res.render('index')
})

app.post('/post', (req, res) => {
  console.log(req.params)
  console.log(req.query)
  console.log(req.body)

  res.render('postform', {
    name: req.body.name,
    color: req.body.color
  })
})

app.listen(8000)