const mongoose = require("mongoose");

const contactSchema = mongoose.Schema({
  name: String,
  description: String
}, { collection: "contacts"});

module.exports = mongoose.model("Contact", contactSchema);
