const express = require('express');

const app = express();

app.use(express.static(__dirname));

app.get('*', (req, res) => {
  res.sendFile(`index.html`, { root: __dirname });
});

const port = process.env.SERVER_PORT || '5000';
app.listen(port, () => console.log(`API running on localhost:${port}`));