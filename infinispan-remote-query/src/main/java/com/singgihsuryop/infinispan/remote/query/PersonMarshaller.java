package com.singgihsuryop.infinispan.remote.query;

import java.io.IOException;

import org.infinispan.protostream.MessageMarshaller;

public class PersonMarshaller implements MessageMarshaller<Person> {
	@Override
	public String getTypeName() {
		return "quickstart.Person";
	}
	@Override
	public Class<? extends Person> getJavaClass() {
		return Person.class;
	}
	@Override
	public void writeTo(ProtoStreamWriter writer, Person person) throws IOException {
		writer.writeString("id", person.getId());
		writer.writeString("name", person.getName());
		writer.writeInt("age", person.getAge());
	}
	@Override
	public Person readFrom(ProtoStreamReader reader) throws IOException {
		String id = reader.readString("id");
		String name = reader.readString("name");
		int age = reader.readInt("age");
		Person person =  new Person(id, name, age);
		return person;
	}
}
