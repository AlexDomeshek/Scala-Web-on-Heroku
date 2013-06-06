package org.util

import org.slf4j.LoggerFactory
import java.lang.{Throwable, String}

/**
 * Modified from from http://stackoverflow.com/questions/978252/logging-in-scala/981942#981942
 */
trait Logging {
    outer =>

    import Logging._

    private lazy val _logger = LoggerFactory.getLogger(loggerNameForClass(outer.getClass.getName))

    def traceEnabled = _logger.isTraceEnabled

    def debugEnabled = _logger.isDebugEnabled

    def infoEnabled = _logger.isInfoEnabled

    def warnEnabled = _logger.isWarnEnabled

    def errorEnabled = _logger.isErrorEnabled

    protected def trace(msg: String, refs: Any*) {
        if (_logger.isTraceEnabled)
            _logger.trace(checkFormat(msg, refs))
    }

    protected def trace(t: Throwable, msg: String, refs: Any*) {
        if (_logger.isTraceEnabled)
            _logger.trace(checkFormat(msg, refs), t)
    }

    protected def debug(msg: String, refs: Any*) {
        if (_logger.isDebugEnabled)
            _logger.debug(checkFormat(msg, refs))
    }

    protected def debug(t: Throwable, msg: String, refs: Any*) {
        if (_logger.isDebugEnabled)
            _logger.debug(checkFormat(msg, refs), t)
    }

    protected def info(msg: String, refs: Any*) {
        if (_logger.isInfoEnabled)
            _logger.info(checkFormat(msg, refs))
    }

    protected def info(t: Throwable, msg: String, refs: Any*) {
        if (_logger.isInfoEnabled)
            _logger.info(checkFormat(msg, refs), t)
    }

    protected def warn(msg: String, refs: Any*) {
        if (_logger.isWarnEnabled)
            _logger.warn(checkFormat(msg, refs))
    }

    protected def warn(t: Throwable, msg: String, refs: Any*) {
        if (_logger.isWarnEnabled)
            _logger.warn(checkFormat(msg, refs), t)
    }

    protected def error(msg: String, refs: Any*) {
        if (_logger.isErrorEnabled)
            _logger.error(checkFormat(msg, refs))
    }

    protected def error(t: Throwable, msg: String, refs: Any*) {
        if (_logger.isErrorEnabled)
            _logger.error(checkFormat(msg, refs), t)
    }

    protected def timed(msg: String)(f: => Any) {
        info("starting " + msg)
        val start = System.currentTimeMillis
        f
        val duration = (System.currentTimeMillis - start).toDouble / 1000
        info(msg + " finished in " + duration + " seconds")
    }
}

object Logging {
    private def checkFormat(msg: String, refs: Seq[Any]) =
        if (refs.nonEmpty) msg.format(refs: _*) else msg

    def loggerNameForClass(className: String) = {
        if (className endsWith "$") className.dropRight(1) else className
    }
}