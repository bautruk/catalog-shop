package com.instructure.shop.course.enums;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class CourseTypeTest {

  @ParameterizedTest
  @MethodSource("provideStringAndExpectedType")
  void convert(String input, CourseType expected) {
    //act
    var type = CourseType.valueOf(input);
    //assert
    assertEquals(expected, type);
  }

  @Test()
  void convert_inputIncorrectValue_ThrowException() {
    //arrange
    var typeName = "Biology";
    //act
    assertThrows(IllegalArgumentException.class, () -> CourseType.valueOf(typeName));
  }

  public static Stream<Arguments> provideStringAndExpectedType() {
    return Stream.of(Arguments.of("MATH", CourseType.MATH),
        Arguments.of("PHYSICS", CourseType.PHYSICS));
  }
}