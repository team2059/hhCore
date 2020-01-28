package hhCore.subsystems.elevator;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;

public class ElevatorEncoder extends Encoder {
  /**
   * Encoder constructor. Construct a Encoder given a and b channels.
   *
   * <p>
   *
   * <p>The encoder will start counting immediately.
   *
   * @param channelA The a channel DIO channel. 0-9 are on-board, 10-25 are on the MXP port
   * @param channelB The b channel DIO channel. 0-9 are on-board, 10-25 are on the MXP port
   * @param reverseDirection represents the orientation of the encoder and inverts the output values
   */
  public ElevatorEncoder(int channelA, int channelB, boolean reverseDirection) {
    super(channelA, channelB, reverseDirection);
  }

  /**
   * Encoder constructor. Construct a Encoder given a and b channels.
   *
   * <p>
   *
   * <p>The encoder will start counting immediately.
   *
   * @param channelA The a channel digital input channel.
   * @param channelB The b channel digital input channel.
   */
  double offset = 0.0;

  public double getPosition() {
    return this.getPosition() - offset;
  }

  public void setPosition(double d) {
    offset = this.getPosition() - d;
  }

  public ElevatorEncoder(int channelA, int channelB) {
    super(channelA, channelB);
  }

  /**
   * Encoder constructor. Construct a Encoder given a and b channels.
   *
   * <p>
   *
   * <p>The encoder will start counting immediately.
   *
   * @param channelA The a channel digital input channel.
   * @param channelB The b channel digital input channel.
   * @param reverseDirection represents the orientation of the encoder and inverts the output values
   *     if necessary so forward represents positive values.
   * @param encodingType either k1X, k2X, or k4X to indicate 1X, 2X or 4X decoding. If 4X is
   *     selected, then an encoder FPGA object is used and the returned counts will be 4x the
   *     encoder spec'd value since all rising and falling edges are counted. If 1X or 2X are
   *     selected then a m_counter object will be used and the returned value will either exactly
   *     match the spec'd count
   */
  public ElevatorEncoder(
      int channelA, int channelB, boolean reverseDirection, EncodingType encodingType) {
    super(channelA, channelB, reverseDirection, encodingType);
  }

  /**
   * Encoder constructor. Construct a Encoder given a and b channels. Using an index pulse forces 4x
   * encoding
   *
   * <p>
   *
   * <p>The encoder will start counting immediately.
   *
   * @param channelA The a channel digital input channel.
   * @param channelB The b channel digital input channel.
   * @param indexChannel The index channel digital input channel.
   * @param reverseDirection represents the orientation of the encoder and inverts the output values
   */
  public ElevatorEncoder(int channelA, int channelB, int indexChannel, boolean reverseDirection) {
    super(channelA, channelB, indexChannel, reverseDirection);
  }

  /**
   * Encoder constructor. Construct a Encoder given a and b channels. Using an index pulse forces 4x
   * encoding
   *
   * <p>
   *
   * <p>The encoder will start counting immediately.
   *
   * @param channelA The a channel digital input channel.
   * @param channelB The b channel digital input channel.
   * @param indexChannel The index channel digital input channel.
   */
  public ElevatorEncoder(int channelA, int channelB, int indexChannel) {
    super(channelA, channelB, indexChannel);
  }

  /**
   * Encoder constructor. Construct a Encoder given a and b channels as digital inputs. This is used
   * in the case where the digital inputs are shared. The Encoder class will not allocate the
   * digital inputs and assume that they already are counted.
   *
   * <p>
   *
   * <p>The encoder will start counting immediately.
   *
   * @param sourceA The source that should be used for the a channel.
   * @param sourceB the source that should be used for the b channel.
   * @param reverseDirection represents the orientation of the encoder and inverts the output values
   */
  public ElevatorEncoder(DigitalSource sourceA, DigitalSource sourceB, boolean reverseDirection) {
    super(sourceA, sourceB, reverseDirection);
  }

  /**
   * Encoder constructor. Construct a Encoder given a and b channels as digital inputs. This is used
   * in the case where the digital inputs are shared. The Encoder class will not allocate the
   * digital inputs and assume that they already are counted.
   *
   * <p>
   *
   * <p>The encoder will start counting immediately.
   *
   * @param sourceA The source that should be used for the a channel.
   * @param sourceB the source that should be used for the b channel.
   */
  public ElevatorEncoder(DigitalSource sourceA, DigitalSource sourceB) {
    super(sourceA, sourceB);
  }

  /**
   * Encoder constructor. Construct a Encoder given a and b channels as digital inputs. This is used
   * in the case where the digital inputs are shared. The Encoder class will not allocate the
   * digital inputs and assume that they already are counted.
   *
   * <p>
   *
   * <p>The encoder will start counting immediately.
   *
   * @param sourceA The source that should be used for the a channel.
   * @param sourceB the source that should be used for the b channel.
   * @param reverseDirection represents the orientation of the encoder and inverts the output values
   *     if necessary so forward represents positive values.
   * @param encodingType either k1X, k2X, or k4X to indicate 1X, 2X or 4X decoding. If 4X is
   *     selected, then an encoder FPGA object is used and the returned counts will be 4x the
   *     encoder spec'd value since all rising and falling edges are counted. If 1X or 2X are
   *     selected then a m_counter object will be used and the returned value will either exactly
   *     match the spec'd count
   */
  public ElevatorEncoder(
      DigitalSource sourceA,
      DigitalSource sourceB,
      boolean reverseDirection,
      EncodingType encodingType) {
    super(sourceA, sourceB, reverseDirection, encodingType);
  }

  /**
   * Encoder constructor. Construct a Encoder given a, b and index channels as digital inputs. This
   * is used in the case where the digital inputs are shared. The Encoder class will not allocate
   * the digital inputs and assume that they already are counted.
   *
   * <p>
   *
   * <p>The encoder will start counting immediately.
   *
   * @param sourceA The source that should be used for the a channel.
   * @param sourceB the source that should be used for the b channel.
   * @param indexSource the source that should be used for the index channel.
   * @param reverseDirection represents the orientation of the encoder and inverts the output values
   */
  public ElevatorEncoder(
      DigitalSource sourceA,
      DigitalSource sourceB,
      DigitalSource indexSource,
      boolean reverseDirection) {
    super(sourceA, sourceB, indexSource, reverseDirection);
  }

  /**
   * Encoder constructor. Construct a Encoder given a, b and index channels as digital inputs. This
   * is used in the case where the digital inputs are shared. The Encoder class will not allocate
   * the digital inputs and assume that they already are counted.
   *
   * <p>
   *
   * <p>The encoder will start counting immediately.
   *
   * @param sourceA The source that should be used for the a channel.
   * @param sourceB the source that should be used for the b channel.
   * @param indexSource the source that should be used for the index channel.
   */
  public ElevatorEncoder(DigitalSource sourceA, DigitalSource sourceB, DigitalSource indexSource) {
    super(sourceA, sourceB, indexSource);
  }
}
