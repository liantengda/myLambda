package com.lian.myLambda.customizeInterface;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * 一个支持串行集合操作和并行集合操作的元素序列，以下例子将阐述如何通过使用Stream接口和IntStream接口进行一个集合操作
 * <pre>{@code
 *     int sum = widgets.stream()
 *                      .filter(w -> w.getColor() == RED)
 *                      .mapToInt(w -> w.getWeight())
 *                      .sum();
 * }</pre>
 *
 * 在这个例子中，widgets是一个集合，里面装的是一堆小部件（widget）对象，我们通过Collection接口的steam方法创建了一个装有
 * 小部件的stream，然后根据第一个条件过滤出了颜色为红色的小部件stream，之后我们取每个红色小部件的重量属性，组成了一个
 * 只含有重量属性的stream，重量属性的类型是int类型，之后把重量stream中的数值全部相加得到总重量，整个过程总结起来如下：
 * 计算一堆小部件中，红色小部件的总重量。
 *
 * 除了Object引用的Stream之外，还有一些原始的专用Stream，比如 IntStream,LongStream,DoubleStream,这些都可以被称作
 * streams 同时都遵从这里所提到的规则与约束。
 *
 * 为了进行计算，stream操作被组装进一个stream管道。一个stream管道由数据源、0个或者多个中间操作和一个最终操作组成。其中
 * 数据源指的可能是一个数组，一个集合，一个生成函数或者一个I/O通道等等，中间操作指的是通过map或者filter等这样的方法将steam
 * 转换为另一个stream的操作，最终操作指的是产生一个结果集或者起到一种作用，例如Stream接口的count方法或者Stream接口的forEach方法。
 * Stream的操作具有延迟性，只有在需要结果的时候这一过程才会被执行。
 *
 * <p>Collections and streams, while bearing some superficial similarities,
 * have different goals.  Collections are primarily concerned with the efficient
 * management of, and access to, their elements.  By contrast, streams do not
 * provide a means to directly access or manipulate their elements, and are
 * instead concerned with declaratively describing their source and the
 * computational operations which will be performed in aggregate on that source.
 * However, if the provided stream operations do not offer the desired
 * functionality, the {@link #iterator()} and {@link #spliterator()} operations
 * can be used to perform a controlled traversal.
 *
 * 集合和streams，虽然看起来很像，但是它们的目标结果是不一样的。集合主要是用来有效的管理元素，存储和获取元素的。
 * 相反，streams不提供对它们元素的直接操作和存储获取，它是来描述它们的数据源与在即将发生在数据源上的计算操作的。
 * 然而，如果提供的stream操作不支持所期望的功能，iterator方法和spliterator方法可以被用来进行一次可控制的遍历。
 *
 * <p>A stream pipeline, like the "widgets" example above, can be viewed as
 * a <em>query</em> on the stream source.  Unless the source was explicitly
 * designed for concurrent modification (such as a {@link java.util.concurrent.ConcurrentHashMap}),
 * unpredictable or erroneous behavior may result from modifying the stream
 * source while it is being queried.
 * 一个stream管道，像上例中的widgets，可以被看作是一次对stream数据源的查询操作。如果stream数据源是Concurrent系列的
 * 集合，那么在对其进行变更操作的时候，查询会报错。
 *
 * <p>Most stream operations accept parameters that describe user-specified
 * behavior, such as the lambda expression {@code w -> w.getWeight()} passed to
 * {@code mapToInt} in the example above.  To preserve correct behavior,
 * these <em>behavioral parameters</em>:
 * <ul>
 * <li>must be <a href="package-summary.html#NonInterference">non-interfering</a>
 * (they do not modify the stream source); and</li>
 * <li>in most cases must be <a href="package-summary.html#Statelessness">stateless</a>
 * (their result should not depend on any state that might change during execution
 * of the stream pipeline).</li>
 * </ul>
 *
 * 大多数stream操作 可以接受描述用户具体行为的参数，例如lambda表达式，如上例所示。为了维持正确的行为，这些行为参数
 * 一定不能对数据源产生干扰（如修改行为）并且 在大多数案例中，这些参数一定要是无状态的（在stream管道的执行期间，它们的结果
 * 不能受到任何其他方面的影响，即，它们永远不会因为外界因素而改变）。
 *
 * <p>Such parameters are always instances of a
 * <a href="../function/package-summary.html">functional interface</a> such
 * as {@link java.util.function.Function}, and are often lambda expressions or
 * method references.  Unless otherwise specified these parameters must be
 * <em>non-null</em>.
 * 这样的参数总是 为函数式接口，并且经常是一个lambda表达式或者是一个方法的引用
 * 除非另有说明，这些参数必须不为空
 *
 * <p>A stream should be operated on (invoking an intermediate or terminal stream
 * operation) only once.  This rules out, for example, "forked" streams, where
 * the same source feeds two or more pipelines, or multiple traversals of the
 * same stream.  A stream implementation may throw {@link IllegalStateException}
 * if it detects that the stream is being reused. However, since some stream
 * operations may return their receiver rather than a new stream object, it may
 * not be possible to detect reuse in all cases.
 * rule out 取消，把某件事情或者某种可能性排除在外
 * 一个stream 应该只能被操作一次（调用中间操作，或者最终操作）。这里需要排除一种情况，那就是并行streams，即，相同的
 * stream数据源，进入两个或者多个管道，或者对同一个stream进行多重遍历。
 * 一个stream的实现可能要抛出IllegalStateException。如果它检测到stream被重复使用。
 * 然而，因为一些stream操作可能返回给它们的接收者而不是一个新的stream对象，所以并不是所有的情况下都能检测到stream被
 * 重复使用
 * <p>Streams have a {@link #close()} method and implement {@link AutoCloseable},
 * but nearly all stream instances do not actually need to be closed after use.
 * Generally, only streams whose source is an IO channel (such as those returned
 * by {@link Files#lines(Path, Charset)}) will require closing.  Most streams
 * are backed by collections, arrays, or generating functions, which require no
 * special resource management.  (If a stream does require closing, it can be
 * declared as a resource in a {@code try}-with-resources statement.)
 * Streams有一个close方法和实现，但是几乎所有的stream实际上在使用后并不需要执行close操作。通常，只有流的数据源是一个IO
 * 管道时才要求关闭（如Files中的lines（）方法），大多数stream 返回集合，数组或者正在生成的方法，这些都要求没有专门的流
 * 管理。
 * <p>Stream pipelines may execute either sequentially or in
 * <a href="package-summary.html#Parallelism">parallel</a>.  This
 * execution mode is a property of the stream.  Streams are created
 * with an initial choice of sequential or parallel execution.  (For example,
 * {@link java.util.Collection#stream() Collection.stream()} creates a sequential stream,
 * and {@link java.util.Collection#parallelStream() Collection.parallelStream()} creates
 * a parallel one.)  This choice of execution mode may be modified by the
 * {@link #sequential()} or {@link #parallel()} methods, and may be queried with
 * the {@link #isParallel()} method.
 *
 * 流管道可能串行执行，也可能并行执行。这种执行模式是stream的一个特性，Streams在创建时可以选择串行还是并行。例如
 * Collection的stream（）方法，就是创建一个串行的stream。Collection的parallelStream就是创建一个并行的stream。
 * 这种执行方式的选择可以通过sequential和parallel方法被改变，也可以使用isParallel方法进行查询。
 * @param <T> the type of the stream elements
 * @since 1.8
 * @see java.util.stream.IntStream
 * @see java.util.stream.LongStream
 * @see java.util.stream.DoubleStream
 * @see <a href="package-summary.html">java.util.stream</a>
 */
public interface MyStream {

}
