package core

import javax.servlet.http.{HttpServletResponse => Resp}

class Result(status: Int)

trait Informational

final class Continue extends Result(Resp.SC_CONTINUE) with Informational

final class SwitchingProtocols extends Result(Resp.SC_SWITCHING_PROTOCOLS) with Informational

final class Processing extends Result(102) with Informational

trait Success

final case class Ok(contesnts: String) extends Result(Resp.SC_OK) with Success 

final class Created extends Result(Resp.SC_CREATED) with Success

final class Accepted extends Result(Resp.SC_ACCEPTED) with Success

final class NonAuthoritativeInformation extends Result(Resp.SC_NON_AUTHORITATIVE_INFORMATION) with Success

final class NoContent extends Result(Resp.SC_NO_CONTENT) with Success

final class ResetContent extends Result(Resp.SC_RESET_CONTENT) with Success

final class PartialContent extends Result(Resp.SC_PARTIAL_CONTENT) with Success

final class MultiStatus extends Result(207) with Success

final class AlreadyReported extends Result(208) with Success

final class IMUsed extends Result(226) with Success

trait Redirection

final class MultipleChoices extends Result(Resp.SC_MULTIPLE_CHOICES) with Redirection

final class MovedPermanently extends Result(Resp.SC_MOVED_PERMANENTLY) with Redirection

final class Found extends Result(Resp.SC_FOUND) with Redirection

final class SeeOther extends Result(Resp.SC_SEE_OTHER) with Redirection

final class NotModified extends Result(Resp.SC_NOT_MODIFIED) with Redirection

final class UseProxy extends Result(Resp.SC_USE_PROXY) with Redirection

final class TemporaryRedirect extends Result(Resp.SC_TEMPORARY_REDIRECT) with Redirection

final class PermanentRedirect extends Result(308) with Redirection

trait ClientError

final class BadRequest extends Result(Resp.SC_BAD_REQUEST) with ClientError

final class Unauthorized extends Result(Resp.SC_UNAUTHORIZED) with ClientError

final class PaymentRequired extends Result(Resp.SC_PAYMENT_REQUIRED) with ClientError

final class Forbidden extends Result(Resp.SC_FORBIDDEN) with ClientError

final class NotFound extends Result(Resp.SC_NOT_FOUND) with ClientError

final class MehtodNotAllowed extends Result(Resp.SC_METHOD_NOT_ALLOWED) with ClientError

final class NotAcceptable extends Result(Resp.SC_NOT_ACCEPTABLE) with ClientError

final class ProxyAuthenticationRequired extends Result(Resp.SC_PROXY_AUTHENTICATION_REQUIRED) with ClientError

final class RequestTimeout extends Result(Resp.SC_REQUEST_TIMEOUT) with ClientError

final class Conflict extends Result(Resp.SC_CONFLICT) with ClientError

final class Gone extends Result(Resp.SC_GONE) with ClientError

final class LengthRequired extends Result(Resp.SC_LENGTH_REQUIRED) with ClientError

final class PreconditionFailed extends Result(Resp.SC_PRECONDITION_FAILED) with ClientError

final class RequestEntityTooLarge extends Result(Resp.SC_REQUEST_ENTITY_TOO_LARGE) with ClientError

final class RequestURITooLong extends Result(Resp.SC_REQUEST_URI_TOO_LONG) with ClientError

final class UnsupportedMediaType extends Result(Resp.SC_UNSUPPORTED_MEDIA_TYPE) with ClientError

final class RequestedRangeNotSatisfiable extends Result(Resp.SC_REQUESTED_RANGE_NOT_SATISFIABLE) with ClientError

final class ExpectationFailed extends Result(Resp.SC_EXPECTATION_FAILED) with ClientError

final class AuthenticationTimeout extends Result(419) with ClientError

final class MethodFailure extends Result(420) with ClientError

final class EnhaceYourCalm extends Result(420) with ClientError

final class UnprocessableEntity extends Result(422) with ClientError

final class Locked extends Result(423) with ClientError

final class FailedDependency extends Result(424) with ClientError

final class UpgradeRequired extends Result(426) with ClientError

final class PreconditionRequired extends Result(428) with ClientError

final class TooManyRequest extends Result(429) with ClientError

final class RequestHeaderFieldsTooLarge extends Result(431) with ClientError

final class LoginTimeout extends Result(440) with ClientError

final class NoResponse extends Result(444) with ClientError

final class RetryWith extends Result(449) with ClientError

final class BlockedByWindowsParentalControls extends Result(450) with ClientError

final class UnavailableForLegalReasons extends Result(451) with ClientError

final class Redirect extends Result(451) with ClientError

final class NginxRequestHeaderTooLarge extends Result(494) with ClientError

final class CertError extends Result(495) with ClientError

final class HttpToHttps extends Result(497) with ClientError

final class TokenExpiredOrInvalid extends Result(498) with ClientError

final class ClientClosedRequest extends Result(499) with ClientError

final class TokenRequired extends Result(499) with ClientError

trait ServerError

final class InternalServerError extends Result(Resp.SC_INTERNAL_SERVER_ERROR) with ServerError

final class NotImplemented extends Result(Resp.SC_NOT_IMPLEMENTED) with ServerError

final class BadGateway extends Result(Resp.SC_BAD_GATEWAY) with ServerError

final class ServiceUnavailable extends Result(Resp.SC_SERVICE_UNAVAILABLE) with ServerError

final class GatewayTimeout extends Result(Resp.SC_GATEWAY_TIMEOUT) with ServerError

final class HttpVersionNotSupported extends Result(Resp.SC_HTTP_VERSION_NOT_SUPPORTED) with ServerError

final class VariantAlsoNegotiates extends Result(506) with ServerError

final class InsufficientStorage extends Result(507) with ServerError

final class LoopDetected extends Result(508) with ServerError

final class BandwidthLimitExceeded extends Result(509) with ServerError

final class NotExtended extends Result(510) with ServerError

final class NetworkAuthenticationRequired extends Result(511) with ServerError

final class OriginError extends Result(520) with ServerError

final class WebServerIsDown extends Result(521) with ServerError

final class ConnectionTimeout extends Result(522) with ServerError

final class ProxyDeclinedRequest extends Result(523) with ServerError

final class ATimeoutOccurred extends Result(524) with ServerError

final class NetworkReadTimeoutError extends Result(598) with ServerError

final class NetworkConnectTimeoutError extends Result(599) with ServerError


object Result {
  
}