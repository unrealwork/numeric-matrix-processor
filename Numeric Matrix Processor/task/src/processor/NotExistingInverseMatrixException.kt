package processor

import java.lang.RuntimeException

class NotExistingInverseMatrixException(override val message: String? = null) : RuntimeException(message)
