package org.mow.it.now.common;

import java.util.function.Consumer;

public interface Either<L, R> {

    void fold(Consumer<L> leftConsumer, Consumer<R> rightConsumer);

    static <L, R> Either<L, R> ofLeft(L left) {
        return new Left<>(left);
    }

    static <L, R> Either<L, R> ofRight(R right) {
        return new Right<>(right);
    }

    class Left<L, R> implements Either<L, R> {
        private final L left;

        public Left(L left) {
            this.left = left;
        }

        @Override
        public void fold(Consumer<L> leftConsumer, Consumer<R> rightConsumer) {
            leftConsumer.accept(left);
        }
    }

    class Right<T, R> implements Either<T, R> {
        private final R right;

        public Right(R right) {
            this.right = right;
        }

        @Override
        public void fold(Consumer<T> leftConsumer, Consumer<R> rightConsumer) {
            rightConsumer.accept(right);
        }
    }
}
