package processor

import java.lang.RuntimeException

class NotMatchingDimensionsException(override val message: String? = null) : RuntimeException() {
}
