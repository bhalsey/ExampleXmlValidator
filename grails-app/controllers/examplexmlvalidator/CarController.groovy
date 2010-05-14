package examplexmlvalidator

import org.xml.sax.SAXException

class CarController {

    def index = { }

    /** pretend to add cars to something.  Doesn't perform schema validation. */
    def add = {
	def xml = request.XML
	int count = 0
	xml.car.each { car ->
	    log.warn "Adding car of make ${car.@make}"
	    log.warn "has a record of type: ${car.record.@type} in ${car.record.text()}"
	    // add cars here
	    count++
	}

	render(contentType: "text/xml") {
	    message("Added ${count} cars")
	}
    }

    /** pretend to add cars to something.  Performs schema validation. */
    def addValidate = {
	try {
	    def xml = request.XML(carRecordsSchema)

	    // test that we actually cached our xml object
	    xml = request.XML

	    int count = 0
	    xml.car.each { car ->
		log.warn "Adding car of make ${car.@make}"
		log.warn "has a record of type: ${car.record.@type} in ${car.record.text()}"
		// add cars here
		count++
	    }

	    render(contentType: "text/xml") {
		message("Added ${count} cars.")
	    }
	}
	catch (SAXException e) {
	    render(contentType: "text/xml") {
		message("Error in xml schema: " + e.message)
	    }
	}
    }

    /** pretend to add cars to something.  Performs schema validation. */
    def addValidateSchemaFile = {
	try {
	    def xml = request.XML("/xsd/car-records.xsd")

	    // test that we actually cached our xml object
	    xml = request.XML

	    int count = 0
	    xml.car.each { car ->
		log.warn "Adding car of make ${car.@make}"
		log.warn "has a record of type: ${car.record.@type} in ${car.record.text()}"
		// add cars here
		count++
	    }

	    render(contentType: "text/xml") {
		message("Added ${count} cars.")
	    }
	}
	catch (SAXException e) {
	    render(contentType: "text/xml") {
		message("Error in xml schema: " + e.message)
	    }
	}
    }

    /** pretend to add cars to something.  Tests that we still perform schema 
     *  even after calling request.XML, which caches the parsed xml object.
     */
    def addTestCache = {
	try {
	    def tempXml = request.XML
	    def xml = request.XML(carRecordsSchema)

	    // test that we actually cached our xml object
	    xml = request.XML

	    int count = 0
	    xml.car.each { car ->
		log.warn "Adding car of make ${car.@make}"
		log.warn "has a record of type: ${car.record.@type} in ${car.record.text()}"
		// add cars here
		count++
	    }

	    render(contentType: "text/xml") {
		message("Added ${count} cars.")
	    }
	}
	catch (SAXException e) {
	    render(contentType: "text/xml") {
		message("Error in xml schema: " + e.message)
	    }
	}
    }

    String carRecordsSchema = '''
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" elementFormDefault="qualified">
  <xs:element name="records">
    <xs:complexType>
      <xs:sequence>
        <xs:element maxOccurs="unbounded" ref="car"/>
      </xs:sequence>
    </xs:complexType>
  </xs:element>
  <xs:element name="car">
    <xs:complexType>
      <xs:sequence>
        <xs:element ref="country"/>
        <xs:element ref="record"/>
      </xs:sequence>
      <xs:attribute name="make" use="required" type="xs:NCName"/>
      <xs:attribute name="name" use="required"/>
      <xs:attribute name="year" use="required" type="xs:integer"/>
    </xs:complexType>
  </xs:element>
  <xs:element name="country" type="xs:string"/>
  <xs:element name="record">
    <xs:complexType mixed="true">
      <xs:attribute name="type" use="required" type="xs:NCName"/>
    </xs:complexType>
  </xs:element>
</xs:schema>
'''
}
