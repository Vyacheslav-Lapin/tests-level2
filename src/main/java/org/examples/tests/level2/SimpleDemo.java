package org.examples.tests.level2;

import io.vavr.CheckedPredicate;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Builder;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
@UtilityClass
public class SimpleDemo {

  @SneakyThrows
  private static <T> Collection<T> forEach(ResultSet resultSet, Function<ResultSet, T> mapper) {

    //    val ts = new ArrayList<T>();
    //    while (resultSet.next())
    //      ts.add(mappes.apply(resultSet));
    //    return ts;

    return Stream.generate(() -> resultSet)
               .takeWhile(CheckedPredicate.of(ResultSet::next).unchecked())
               .map(mapper)
               .collect(Collectors.toList());
  }

  @SneakyThrows
  public void main(String... __) {
    @Cleanup val connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    @Cleanup val statement = connection.createStatement();
    statement.executeUpdate("create table student (id identity, name varchar not null, groupId int)");
    statement.executeUpdate("insert into student (name, groupId) values ( 'Вася Пупкин', 123456 ), ('Федя Прокопов', 654321)");
    @Cleanup val resultSet = statement.executeQuery("select id, name, groupId as groupId from student");
    forEach(resultSet, Student::getStudent)
        .forEach(student -> log.info(student.toString()));
  }
}

@Value
@Builder
@FieldNameConstants
class Student {
  Long id;
  String name;
  int groupId;

  @SneakyThrows
  public static Student getStudent(ResultSet resultSet) {
    return builder()
               .id(resultSet.getLong(Fields.id))
               .name(resultSet.getString(Fields.name))
               .groupId(resultSet.getInt(Fields.groupId)).build();
  }
}
