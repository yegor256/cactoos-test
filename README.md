<img src="http://cf.jare.io/?u=http%3A%2F%2Fwww.yegor256.com%2Fimages%2Fbooks%2Felegant-objects%2Fcactus.svg" height="100px" />

**ATTENTION**: It is still just a idea.

We are not happy with
[Hamcrest](http://hamcrest.org/JavaHamcrest/),
[AssertJ](http://joel-costigliola.github.io/assertj/), and
[JUnit](http://junit.org/junit4/) because
they are procedural and not object-oriented. They do their job,
but mostly through static methods. This library is
doing almost exactly the same, but through objects.

The library has no dependencies. All you need is this
(get the latest version [here](https://github.com/yegor256/cactoos-test/releases)):

```xml
<dependency>
  <groupId>org.cactoos</groupId>
  <artifactId>cactoos-test</artifactId>
</dependency>
```

Java version required: 1.8+.

## How to use?

First, you create your first test case (class names are verbs):

```java
public class Multiplies implements TestCase {
  @Override
  void test() throws Exception {
    new Match<>(2 * 2, new EqualTo<>(4)).test();
  }
}
```

Or even simpler:

```java
public class Multiplies extends TestEnvelope {
  Multiplies() {
    super(new Match<>(2 * 2, new EqualTo<>(4)));
  }
}
```

Then, you create the main entry point to the entire test library of yours:


```java
public class Tests extends TestEntry {
  @Override
  void test() throws Exception {
    new TestSuite(
      new Multiplies(),
      new AddsTwoIntegers(),
      new SubtractsDoubles()
    ).test();
  }
}
```

Then, you just run this `Tests`, through its static `main()` inherited
from `TestEntry`.

## How to contribute?

Just fork the repo and send us a pull request.

Make sure your branch builds without any warnings/issues:

```
mvn clean install -Pqulice
```

## License (MIT)

Copyright (c) 2017 Yegor Bugayenko

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included
in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
