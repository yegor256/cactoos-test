/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.cactoos.test;

import java.util.Objects;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.IterableOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.text.JoinedText;
import org.cactoos.text.TextOf;

/**
 * Test Suite.
 *
 * @since 0.1
 */
public final class TestSuite implements TestCase {

    /**
     * Test cases.
     */
    private final Iterable<TestCase> cases;

    /**
     * Ctor.
     * @param cases Test cases
     */
    public TestSuite(final TestCase... cases) {
        this(new IterableOf<>(cases));
    }

    /**
     * Ctor.
     * @param cases Test cases
     */
    public TestSuite(final Iterable<TestCase> cases) {
        this.cases = cases;
    }

    @Override
    @SuppressWarnings({
        "PMD.AvoidThrowingRawExceptionTypes",
        "PMD.AvoidCatchingGenericException"})
    public void test() throws Exception {
        // @checkstyle IllegalCatchCheck (15 lines)
        final String result = new JoinedText(
            new TextOf(", "),
            new Mapped<>(
                exception -> new TextOf(exception.getMessage()),
                new Filtered<>(
                    Objects::nonNull,
                    new Mapped<>(
                        test -> {
                            Exception error = null;
                            try {
                                test.test();
                            } catch (final Exception exception) {
                                error = exception;
                            }
                            return error;
                        },
                        this.cases
                    )
                )
            )
        ).asString();
        if (!"".equals(result)) {
            throw new Exception(result);
        }
    }
}
