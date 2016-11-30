package commbank.grpcs
import Service._

object Filter {
  trait Filter[Req, Rep] {
    def apply(r: Req, s: Service[Req, Rep]): Service[Req, Rep]
  }

  implicit class FilterSyntax[Req, Rep](f: Filter[Req, Rep]) {
    def andThen(other: Filter[Req, Rep]): Filter[Req, Rep] = new Filter[Req, Rep] {
      def apply(r: Req, s: Service[Req, Rep]) = other(r, f(r, s))
    }
    def andThen(s: Service[Req, Rep]): Service[Req, Rep] = r ⇒ f(r, s)(r)
  }
}

