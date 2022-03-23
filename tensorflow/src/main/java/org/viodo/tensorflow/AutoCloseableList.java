package org.viodo.tensorflow;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 文件描述
 *
 * @author xc.chen
 * @date 2021/8/27 13:17
 */
public final class AutoCloseableList<E extends AutoCloseable> extends ArrayList<E> implements AutoCloseable {

    public AutoCloseableList(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public void close() throws Exception {
        Exception toThrow = null;
        for (AutoCloseable c : this) {
            try {
                c.close();
            } catch (Exception e) {
                toThrow = e;
            }
        }
        if (toThrow != null) {
            throw new RuntimeException(toThrow);
        }
    }
}
