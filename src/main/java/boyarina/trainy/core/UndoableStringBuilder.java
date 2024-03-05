package boyarina.trainy.core;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Stack;

@Data
public class UndoableStringBuilder {
    private StringBuilder stringBuilder;
    private Stack<Command> commandHistory;

    public UndoableStringBuilder() {
        new StringBuilder();
    }

    public UndoableStringBuilder(int capacity) {
        new StringBuilder((capacity));
    }

    public UndoableStringBuilder(String str) {
        new StringBuilder((str));
    }

    public UndoableStringBuilder(CharSequence seq) {
        new StringBuilder((seq));
    }

    public SnapShot makeSnapshot() {
        return new SnapShot(this);
    }

    public void undo() {
        commandHistory.pop().undo();
    }

    public StringBuilder append(Object o) {
        commandHistory.add(new Command(makeSnapshot()));
        return stringBuilder.append(o);
    }

    // и еще 12 перегрузок метода append

    public StringBuilder appendCodePoint(int codePoint) {
        commandHistory.add(new Command(makeSnapshot()));
        return stringBuilder.appendCodePoint(codePoint);
    }

    public StringBuilder delete(int start, int end) {
        commandHistory.add(new Command(makeSnapshot()));
        return stringBuilder.delete(start, end);
    }

    public StringBuilder replace(int start, int end, String str) {
        commandHistory.add(new Command(makeSnapshot()));
        return stringBuilder.replace(start, end, str);
    }

    public StringBuilder insert(int index, char[] str, int offset, int len) {
        commandHistory.add(new Command(makeSnapshot()));
        return stringBuilder.insert(index, str, offset, len);
    }

    // и еще 11 перегрузок метода insert

    public int indexOf(String str) {
        commandHistory.add(new Command(makeSnapshot()));
        return stringBuilder.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        commandHistory.add(new Command(makeSnapshot()));
        return stringBuilder.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        commandHistory.add(new Command(makeSnapshot()));
        return stringBuilder.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        commandHistory.add(new Command(makeSnapshot()));
        return stringBuilder.lastIndexOf(str, fromIndex);
    }

    public StringBuilder reverse() {
        commandHistory.add(new Command(makeSnapshot()));
        return stringBuilder.reverse();
    }

    @Data
    @AllArgsConstructor
    public class Command {
        private SnapShot snapShot;

        public void undo() {
            if (snapShot != null) {
                snapShot.restore();
            }
        }
    }

    @Data
    @AllArgsConstructor
    public class SnapShot {
        private final UndoableStringBuilder undoableStringBuilder;

        public void restore() {
            UndoableStringBuilder thisUndoableStrBuild = UndoableStringBuilder.this;
            thisUndoableStrBuild = undoableStringBuilder;
        }
    }
}