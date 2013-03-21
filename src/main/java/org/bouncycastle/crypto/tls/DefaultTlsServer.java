package org.bouncycastle.crypto.tls;

import java.io.IOException;

public abstract class DefaultTlsServer extends AbstractTlsServer {

    public DefaultTlsServer() {
        super();
    }

    public DefaultTlsServer(TlsCipherFactory cipherFactory) {
        super(cipherFactory);
    }

    protected int[] getCipherSuites() {
        return new int[] { CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA, };
    }

    public TlsCredentials getCredentials() {
        // TODO Auto-generated method stub
        return null;
    }

    public TlsKeyExchange getKeyExchange() throws IOException {
        switch (selectedCipherSuite)
        {
            case CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA:
            case CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA:
            case CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA:
            case CipherSuite.TLS_RSA_WITH_RC4_128_SHA:
                return createRSAKeyExchange();

            case CipherSuite.TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA:
            case CipherSuite.TLS_DH_DSS_WITH_AES_128_CBC_SHA:
            case CipherSuite.TLS_DH_DSS_WITH_AES_256_CBC_SHA:
                return createDHKeyExchange(KeyExchangeAlgorithm.DH_DSS);

            case CipherSuite.TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA:
            case CipherSuite.TLS_DH_RSA_WITH_AES_128_CBC_SHA:
            case CipherSuite.TLS_DH_RSA_WITH_AES_256_CBC_SHA:
                return createDHKeyExchange(KeyExchangeAlgorithm.DH_RSA);

            case CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA:
                return createDHEKeyExchange(KeyExchangeAlgorithm.DHE_DSS);

            case CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA:
                return createDHEKeyExchange(KeyExchangeAlgorithm.DHE_RSA);

            case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA:
                return createECDHKeyExchange(KeyExchangeAlgorithm.ECDH_ECDSA);

            case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA:
                return createECDHEKeyExchange(KeyExchangeAlgorithm.ECDHE_ECDSA);

            case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA:
            case CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA:
                return createECDHKeyExchange(KeyExchangeAlgorithm.ECDH_RSA);

            case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA:
            case CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA:
                return createECDHEKeyExchange(KeyExchangeAlgorithm.ECDHE_RSA);

            default:
                /*
                 * Note: internal error here; the TlsProtocol implementation verifies that the
                 * server-selected cipher suite was in the list of client-offered cipher
                 * suites, so if we now can't produce an implementation, we shouldn't have
                 * offered it!
                 */
                throw new TlsFatalAlert(AlertDescription.internal_error);
        }
    }

    public TlsCipher getCipher() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    protected TlsKeyExchange createDHKeyExchange(int keyExchange)
    {
        return new TlsDHKeyExchange(keyExchange);
    }

    protected TlsKeyExchange createDHEKeyExchange(int keyExchange)
    {
        return new TlsDHEKeyExchange(keyExchange);
    }

    protected TlsKeyExchange createECDHKeyExchange(int keyExchange)
    {
        return new TlsECDHKeyExchange(keyExchange);
    }

    protected TlsKeyExchange createECDHEKeyExchange(int keyExchange)
    {
        return new TlsECDHEKeyExchange(keyExchange);
    }

    protected TlsKeyExchange createRSAKeyExchange()
    {
        return new TlsRSAKeyExchange();
    }
}
