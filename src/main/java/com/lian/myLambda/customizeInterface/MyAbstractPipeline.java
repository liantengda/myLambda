package com.lian.myLambda.customizeInterface;

import java.util.Spliterator;
import java.util.function.Supplier;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/5/16 17:16
 */
abstract class MyAbstractPipeline {

    private static final String MSG_STREAM_LINKED = "stream has already been operated upon or closed";
    private static final String MSG_CONSUMED = "source already consumed or closed";
    //反向链接到管道链的头部，流的源头，如果本身是源头，这里就是self
    private final MyAbstractPipeline sourceStage;
    //流管道的上游，如果此节点是管道源头，那么上游为null
    private final MyAbstractPipeline previousStage;
    //
    protected final int sourceOrOpFlags;
    //流管道的下游，若是最后一个中间操作，则为null
    private MyAbstractPipeline nextStage;
    //如果是串行的，则是中间操作数，如果是并行的，则是有状态操作数，执行到终端操作的时候会使用到
    private int depth;

    private int combinedFlags;
    //数据源的spliterator,只在管道头有效，在管道被消费前，若为非空，则sourceSupplier必须为空。
    //在管道被消费后，若为非空，则将其置为null
    private Spliterator<?> sourceSpliterator;
    //源的supplier，只在管道头有效，在管道被消费前，若为非空，则sourceSpliterator必须为null
    //在管道被消费后，若为非空，则将其置为空
    private Supplier<? extends Spliterator<?>> sourceSupplier;
    //如果此管道已连接或者使用，则为真
    private boolean linkedOrConsumed;

    private boolean sourceAnyStateful;

    private Runnable sourceCloseAction;
    //是否并行，只在流的源阶段有效
    private boolean parallel;

    /**
     * Constructor for the head of a stream pipeline.
     *
     * @param source {@code Supplier<Spliterator>} describing the stream source
     * @param sourceFlags The source flags for the stream source, described in
     * {@link StreamOpFlag}
     * @param parallel True if the pipeline is parallel
     */
    MyAbstractPipeline(Supplier<? extends Spliterator<?>> source,
                     int sourceFlags, boolean parallel) {
        this.previousStage = null;
        this.sourceSupplier = source;
        this.sourceStage = this;
        this.sourceOrOpFlags = sourceFlags & StreamOpFlag.STREAM_MASK;
        // The following is an optimization of:
        // StreamOpFlag.combineOpFlags(sourceOrOpFlags, StreamOpFlag.INITIAL_OPS_VALUE);
        this.combinedFlags = (~(sourceOrOpFlags << 1)) & StreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = parallel;
    }
}
