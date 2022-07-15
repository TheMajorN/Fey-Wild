package com.themajorn.feywild.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class BookStackBlock extends HorizontalBlock {

    public static final VoxelShape SHAPE_N = Stream.of(
            Block.box(4.150000000000006, 10.250000000000004, 6.349999999999998, 10.200000000000006, 10.55, 13.549999999999997),
            Block.box(10.200000000000006, 10.100000000000001, 6.149999999999999, 10.300000000000006, 10.750000000000004, 13.649999999999997),
            Block.box(3.950000000000006, 10.550000000000004, 6.149999999999999, 10.200000000000006, 10.750000000000004, 13.649999999999997),
            Block.box(3.950000000000006, 10.100000000000001, 6.149999999999999, 10.200000000000006, 10.3, 13.649999999999997),
            Block.box(5.350000000000001, 4.8500000000000005, 5.249999999999999, 11.400000000000002, 5.15, 12.450000000000001),
            Block.box(11.400000000000002, 4.700000000000001, 5.05, 11.500000000000002, 5.3500000000000005, 12.55),
            Block.box(5.150000000000002, 5.150000000000001, 5.05, 11.400000000000002, 5.350000000000001, 12.55),
            Block.box(5.150000000000002, 4.7, 5.05, 11.400000000000002, 4.9, 12.55),
            Block.box(2.7363410618794557, 14.150000000000004, 5.425780325286659, 9.286341061879451, 15.350000000000003, 13.625780325286659),
            Block.box(2.636341061879456, 14.000000000000002, 5.325780325286658, 2.7363410618794557, 15.450000000000005, 13.825780325286656),
            Block.box(2.7363410618794557, 15.250000000000004, 5.325780325286658, 9.48634106187945, 15.450000000000005, 13.825780325286656),
            Block.box(2.7363410618794557, 14.000000000000005, 5.325780325286658, 9.48634106187945, 14.200000000000006, 13.825780325286656),
            Block.box(5.163658938120548, 8.750000000000004, 4.756508278974291, 11.713658938120549, 9.95, 12.956508278974288),
            Block.box(11.713658938120549, 8.600000000000001, 4.5565082789742934, 11.813658938120549, 10.05, 13.05650827897429),
            Block.box(4.963658938120549, 9.850000000000001, 4.5565082789742934, 11.713658938120549, 10.05, 13.05650827897429),
            Block.box(4.963658938120549, 8.600000000000001, 4.5565082789742934, 11.713658938120549, 8.8, 13.05650827897429),
            Block.box(5.34194941848537, 3.350000000000001, 5.452204663146576, 11.89194941848537, 4.549999999999999, 13.652204663146577),
            Block.box(11.89194941848537, 3.200000000000001, 5.252204663146578, 11.991949418485369, 4.65, 13.752204663146578),
            Block.box(5.141949418485371, 4.449999999999999, 5.252204663146578, 11.89194941848537, 4.65, 13.752204663146578),
            Block.box(5.141949418485371, 3.2000000000000015, 5.252204663146578, 11.89194941848537, 3.400000000000001, 13.752204663146578),
            Block.box(4.050000000000001, 13.450000000000001, 4.432288604260952, 10.84999999999999, 13.750000000000002, 12.632288604260951),
            Block.box(3.9500000000000006, 13.299999999999999, 4.3322886042609525, 4.050000000000001, 13.950000000000001, 12.83228860426095),
            Block.box(4.050000000000001, 13.750000000000002, 4.3322886042609525, 11.04999999999999, 13.950000000000001, 12.83228860426095),
            Block.box(4.050000000000001, 13.299999999999999, 4.3322886042609525, 11.04999999999999, 13.500000000000002, 12.83228860426095),
            Block.box(3.600000000000003, 8.05, 5.7499999999999964, 10.400000000000006, 8.350000000000001, 13.949999999999996),
            Block.box(10.400000000000006, 7.900000000000001, 5.549999999999997, 10.500000000000005, 8.55, 14.049999999999995),
            Block.box(3.400000000000003, 8.350000000000001, 5.549999999999997, 10.400000000000006, 8.55, 14.049999999999995),
            Block.box(3.400000000000003, 7.900000000000002, 5.549999999999997, 10.400000000000006, 8.100000000000001, 14.049999999999995),
            Block.box(4.800000000000002, 2.65, 4.649999999999999, 11.600000000000001, 2.9499999999999997, 12.85),
            Block.box(11.600000000000001, 2.45, 4.449999999999999, 11.700000000000001, 3.1500000000000004, 12.95),
            Block.box(4.600000000000001, 2.9499999999999997, 4.449999999999999, 11.600000000000001, 3.15, 12.95),
            Block.box(4.600000000000001, 2.4499999999999997, 4.449999999999999, 11.600000000000001, 2.7, 12.95),
            Block.box(5.196782397407009, 11.95, 3.3237997677906397, 11.746782397407003, 13.15, 11.523799767790642),
            Block.box(5.09678239740701, 11.799999999999999, 3.2237997677906396, 5.196782397407009, 13.249999999999998, 11.723799767790641),
            Block.box(5.196782397407009, 13.049999999999999, 3.2237997677906396, 11.946782397407002, 13.249999999999998, 11.723799767790641),
            Block.box(5.196782397407009, 11.8, 3.2237997677906396, 11.946782397407002, 12, 11.723799767790641),
            Block.box(2.7032176025929884, 6.550000000000001, 6.858488836470306, 9.253217602592994, 7.750000000000001, 15.058488836470305),
            Block.box(9.253217602592994, 6.400000000000001, 6.6584888364703065, 9.353217602592993, 7.8500000000000005, 15.158488836470305),
            Block.box(2.503217602592988, 7.65, 6.6584888364703065, 9.253217602592994, 7.8500000000000005, 15.158488836470305),
            Block.box(2.503217602592988, 6.4, 6.6584888364703065, 9.253217602592994, 6.6000000000000005, 15.158488836470305),
            Block.box(5.45, 1.15, 4.449999999999999, 12, 2.3499999999999996, 12.65),
            Block.box(12, 0.9500000000000002, 4.25, 12.1, 2.45, 12.75),
            Block.box(5.25, 2.25, 4.25, 12, 2.45, 12.75),
            Block.box(5.25, 0.95, 4.25, 12, 1.2, 12.75),
            Block.box(3.6500000000000004, 10.950000000000001, 4.3822886042609515, 10.449999999999992, 11.65, 13.08228860426095),
            Block.box(3.5500000000000007, 10.75, 4.282288604260952, 3.6500000000000004, 11.75, 13.28228860426095),
            Block.box(3.6500000000000004, 11.55, 4.282288604260952, 10.649999999999991, 11.75, 13.28228860426095),
            Block.box(3.6500000000000004, 10.749999999999998, 4.282288604260952, 10.649999999999991, 11, 13.28228860426095),
            Block.box(4.0000000000000036, 5.550000000000002, 5.299999999999997, 10.800000000000004, 6.250000000000001, 13.999999999999996),
            Block.box(10.800000000000004, 5.350000000000001, 5.099999999999998, 10.900000000000004, 6.350000000000001, 14.099999999999996),
            Block.box(3.8000000000000034, 6.150000000000001, 5.099999999999998, 10.800000000000004, 6.350000000000001, 14.099999999999996),
            Block.box(3.8000000000000034, 5.350000000000001, 5.099999999999998, 10.800000000000004, 5.600000000000001, 14.099999999999996),
            Block.box(5.2, 0.14999999999999997, 4.199999999999999, 12, 0.8499999999999999, 12.9),
            Block.box(12, -1.1102230246251565e-16, 4, 12.1, 0.95, 13),
            Block.box(5, 0.75, 4, 12, 0.95, 13),
            Block.box(5, 0, 4, 12, 0.19999999999999996, 13),
            Block.box(4.25, 15.549999999999999, 4.832288604260951, 10.29999999999999, 15.850000000000005, 12.03228860426095),
            Block.box(4.15, 15.450000000000005, 4.732288604260951, 4.25, 15.999999999999998, 12.23228860426095),
            Block.box(4.25, 15.850000000000005, 4.732288604260951, 10.49999999999999, 15.999999999999998, 12.23228860426095),
            Block.box(4.25, 15.45, 4.732288604260951, 10.49999999999999, 15.600000000000003, 12.23228860426095)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    public static final VoxelShape SHAPE_E = Stream.of(
            Block.box(3.7057220705780605, 10.250000000000004, 4.081210372245566, 10.90572207057806, 10.55, 10.131210372245567),
            Block.box(3.605722070578061, 10.100000000000001, 10.131210372245569, 11.105722070578059, 10.750000000000004, 10.231210372245569),
            Block.box(3.605722070578061, 10.550000000000004, 3.881210372245567, 11.105722070578059, 10.750000000000004, 10.131210372245567),
            Block.box(3.605722070578061, 10.100000000000001, 3.881210372245567, 11.105722070578059, 10.3, 10.131210372245567),
            Block.box(3.9472153106564534, 4.8500000000000005, 4.5504329132494465, 11.147215310656456, 5.15, 10.600432913249447),
            Block.box(3.8472153106564537, 4.700000000000001, 10.600432913249447, 11.347215310656456, 5.3500000000000005, 10.700432913249447),
            Block.box(3.8472153106564537, 5.150000000000001, 4.350432913249447, 11.347215310656456, 5.350000000000001, 10.600432913249447),
            Block.box(3.8472153106564537, 4.7, 4.350432913249447, 11.347215310656456, 4.9, 10.600432913249447),
            Block.box(5.255589271079968, 14.150000000000004, 4.380835340124667, 13.455589271079967, 15.350000000000003, 10.930835340124663),
            Block.box(5.05558927107997, 14.000000000000002, 4.280835340124668, 13.555589271079969, 15.450000000000005, 4.380835340124667),
            Block.box(5.05558927107997, 15.250000000000004, 4.380835340124667, 13.555589271079969, 15.450000000000005, 11.130835340124662),
            Block.box(5.05558927107997, 14.000000000000005, 4.380835340124667, 13.555589271079969, 14.200000000000006, 11.130835340124662),
            Block.box(3.440707031682166, 8.750000000000004, 4.364091851369993, 11.640707031682163, 9.95, 10.914091851369994),
            Block.box(3.3407070316821645, 8.600000000000001, 10.914091851369994, 11.840707031682161, 10.05, 11.014091851369994),
            Block.box(3.3407070316821645, 9.850000000000001, 4.164091851369994, 11.840707031682161, 10.05, 10.914091851369994),
            Block.box(3.3407070316821645, 8.600000000000001, 4.164091851369994, 11.840707031682161, 8.8, 10.914091851369994),
            Block.box(3.843381385964861, 3.350000000000001, 4.6051814911902476, 12.043381385964862, 4.549999999999999, 11.155181491190246),
            Block.box(3.7433813859648595, 3.200000000000001, 11.155181491190246, 12.24338138596486, 4.65, 11.255181491190246),
            Block.box(3.7433813859648595, 4.449999999999999, 4.405181491190248, 12.24338138596486, 4.65, 11.155181491190246),
            Block.box(3.7433813859648595, 3.2000000000000015, 4.405181491190248, 12.24338138596486, 3.400000000000001, 11.155181491190246),
            Block.box(4.763926833184357, 13.450000000000001, 4.719528100700797, 12.963926833184356, 13.750000000000002, 11.519528100700787),
            Block.box(4.563926833184357, 13.299999999999999, 4.619528100700796, 13.063926833184356, 13.950000000000001, 4.719528100700796),
            Block.box(4.563926833184357, 13.750000000000002, 4.719528100700797, 13.063926833184356, 13.950000000000001, 11.719528100700787),
            Block.box(4.563926833184357, 13.299999999999999, 4.719528100700797, 13.063926833184356, 13.500000000000002, 11.719528100700787),
            Block.box(3.305722070578062, 8.05, 3.531210372245564, 11.505722070578061, 8.350000000000001, 10.331210372245566),
            Block.box(3.2057220705780622, 7.900000000000001, 10.331210372245568, 11.70572207057806, 8.55, 10.431210372245568),
            Block.box(3.2057220705780622, 8.350000000000001, 3.3312103722455637, 11.70572207057806, 8.55, 10.331210372245566),
            Block.box(3.2057220705780622, 7.900000000000002, 3.3312103722455637, 11.70572207057806, 8.100000000000001, 10.331210372245566),
            Block.box(3.547215310656455, 2.65, 4.000432913249447, 11.747215310656456, 2.9499999999999997, 10.800432913249447),
            Block.box(3.447215310656455, 2.45, 10.800432913249447, 11.947215310656455, 3.1500000000000004, 10.900432913249446),
            Block.box(3.447215310656455, 2.9499999999999997, 3.8004329132494465, 11.947215310656455, 3.15, 10.800432913249447),
            Block.box(3.447215310656455, 2.4499999999999997, 3.8004329132494465, 11.947215310656455, 2.7, 10.800432913249447),
            Block.box(4.873415542865812, 11.95, 4.397215310656454, 13.073415542865815, 13.15, 10.947215310656448),
            Block.box(4.673415542865813, 11.799999999999999, 4.297215310656455, 13.173415542865815, 13.249999999999998, 4.397215310656454),
            Block.box(4.673415542865813, 13.049999999999999, 4.397215310656454, 13.173415542865815, 13.249999999999998, 11.147215310656447),
            Block.box(4.673415542865813, 11.8, 4.397215310656454, 13.173415542865815, 12, 11.147215310656447),
            Block.box(2.710733631818231, 6.550000000000001, 3.6381146256211023, 10.91073363181823, 7.750000000000001, 10.188114625621107),
            Block.box(2.6107336318182313, 6.400000000000001, 10.188114625621107, 11.11073363181823, 7.8500000000000005, 10.288114625621107),
            Block.box(2.6107336318182313, 7.65, 3.438114625621102, 11.11073363181823, 7.8500000000000005, 10.188114625621107),
            Block.box(2.6107336318182313, 6.4, 3.438114625621102, 11.11073363181823, 6.6000000000000005, 10.188114625621107),
            Block.box(2.7542806010320575, 1.15, 4.164035189453695, 10.954280601032059, 2.3499999999999996, 10.714035189453694),
            Block.box(2.654280601032058, 0.9500000000000002, 10.714035189453694, 11.154280601032058, 2.45, 10.814035189453694),
            Block.box(2.654280601032058, 2.25, 3.9640351894536945, 11.154280601032058, 2.45, 10.714035189453694),
            Block.box(2.654280601032058, 0.95, 3.9640351894536945, 11.154280601032058, 1.2, 10.714035189453694),
            Block.box(4.313926833184357, 10.950000000000001, 4.319528100700797, 13.013926833184357, 11.65, 11.119528100700789),
            Block.box(4.113926833184358, 10.75, 4.2195281007007965, 13.113926833184356, 11.75, 4.319528100700796),
            Block.box(4.113926833184358, 11.55, 4.319528100700797, 13.113926833184356, 11.75, 11.319528100700788),
            Block.box(4.113926833184358, 10.749999999999998, 4.319528100700797, 13.113926833184356, 11, 11.319528100700788),
            Block.box(3.255722070578061, 5.550000000000002, 3.9312103722455642, 11.95572207057806, 6.250000000000001, 10.731210372245565),
            Block.box(3.1557220705780615, 5.350000000000001, 10.731210372245567, 12.15572207057806, 6.350000000000001, 10.831210372245566),
            Block.box(3.1557220705780615, 6.150000000000001, 3.731210372245564, 12.15572207057806, 6.350000000000001, 10.731210372245565),
            Block.box(3.1557220705780615, 5.350000000000001, 3.731210372245564, 12.15572207057806, 5.600000000000001, 10.731210372245565),
            Block.box(3.497215310656454, 0.1499999999999999, 4.400432913249445, 12.197215310656455, 0.8499999999999999, 11.200432913249445),
            Block.box(3.3972153106564544, -1.1102230246251565e-16, 11.200432913249445, 12.397215310656454, 0.95, 11.300432913249445),
            Block.box(3.3972153106564544, 0.75, 4.200432913249445, 12.397215310656454, 0.95, 11.200432913249445),
            Block.box(3.3972153106564544, 0, 4.200432913249445, 12.397215310656454, 0.19999999999999996, 11.200432913249445),
            Block.box(5.363926833184358, 15.549999999999999, 4.919528100700797, 12.563926833184357, 15.850000000000005, 10.969528100700787),
            Block.box(5.163926833184359, 15.450000000000005, 4.819528100700796, 12.663926833184357, 15.999999999999998, 4.919528100700796),
            Block.box(5.163926833184359, 15.850000000000005, 4.919528100700797, 12.663926833184357, 15.999999999999998, 11.169528100700786),
            Block.box(5.163926833184359, 15.45, 4.919528100700797, 12.663926833184357, 15.600000000000003, 11.169528100700786)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    public static final VoxelShape SHAPE_S = Stream.of(
            Block.box(5.516004938410887, 10.250000000000004, 3.9061549838275056, 11.566004938410888, 10.55, 11.106154983827505),
            Block.box(5.416004938410886, 10.100000000000001, 3.806154983827506, 5.516004938410886, 10.750000000000004, 11.306154983827504),
            Block.box(5.516004938410887, 10.550000000000004, 3.806154983827506, 11.766004938410887, 10.750000000000004, 11.306154983827504),
            Block.box(5.516004938410887, 10.100000000000001, 3.806154983827506, 11.766004938410887, 10.3, 11.306154983827504),
            Block.box(5.046782397407007, 4.8500000000000005, 4.1476482239058985, 11.096782397407008, 5.15, 11.347648223905901),
            Block.box(4.9467823974070075, 4.700000000000001, 4.047648223905899, 5.046782397407007, 5.3500000000000005, 11.5476482239059),
            Block.box(5.046782397407007, 5.150000000000001, 4.047648223905899, 11.296782397407007, 5.350000000000001, 11.5476482239059),
            Block.box(5.046782397407007, 4.7, 4.047648223905899, 11.296782397407007, 4.9, 11.5476482239059),
            Block.box(4.716379970531792, 14.150000000000004, 5.456022184329413, 11.266379970531787, 15.350000000000003, 13.656022184329412),
            Block.box(11.266379970531787, 14.000000000000002, 5.2560221843294155, 11.366379970531787, 15.450000000000005, 13.756022184329414),
            Block.box(4.516379970531792, 15.250000000000004, 5.2560221843294155, 11.266379970531787, 15.450000000000005, 13.756022184329414),
            Block.box(4.516379970531792, 14.000000000000005, 5.2560221843294155, 11.266379970531787, 14.200000000000006, 13.756022184329414),
            Block.box(4.73312345928646, 8.750000000000004, 3.641139944931611, 11.283123459286461, 9.95, 11.841139944931609),
            Block.box(4.633123459286461, 8.600000000000001, 3.5411399449316097, 4.73312345928646, 10.05, 12.041139944931606),
            Block.box(4.73312345928646, 9.850000000000001, 3.5411399449316097, 11.48312345928646, 10.05, 12.041139944931606),
            Block.box(4.73312345928646, 8.600000000000001, 3.5411399449316097, 11.48312345928646, 8.8, 12.041139944931606),
            Block.box(4.492033819466208, 3.350000000000001, 4.043814299214306, 11.042033819466207, 4.549999999999999, 12.243814299214307),
            Block.box(4.392033819466208, 3.200000000000001, 3.9438142992143046, 4.492033819466208, 4.65, 12.443814299214305),
            Block.box(4.492033819466208, 4.449999999999999, 3.9438142992143046, 11.242033819466206, 4.65, 12.443814299214305),
            Block.box(4.492033819466208, 3.2000000000000015, 3.9438142992143046, 11.242033819466206, 3.400000000000001, 12.443814299214305),
            Block.box(4.127687209955667, 13.450000000000001, 4.964359746433802, 10.927687209955657, 13.750000000000002, 13.164359746433801),
            Block.box(10.927687209955659, 13.299999999999999, 4.764359746433803, 11.027687209955658, 13.950000000000001, 13.2643597464338),
            Block.box(3.9276872099556677, 13.750000000000002, 4.764359746433803, 10.927687209955657, 13.950000000000001, 13.2643597464338),
            Block.box(3.9276872099556677, 13.299999999999999, 4.764359746433803, 10.927687209955657, 13.500000000000002, 13.2643597464338),
            Block.box(5.316004938410888, 8.05, 3.506154983827507, 12.11600493841089, 8.350000000000001, 11.706154983827506),
            Block.box(5.216004938410887, 7.900000000000001, 3.4061549838275074, 5.316004938410886, 8.55, 11.906154983827506),
            Block.box(5.316004938410888, 8.350000000000001, 3.4061549838275074, 12.316004938410892, 8.55, 11.906154983827506),
            Block.box(5.316004938410888, 7.900000000000002, 3.4061549838275074, 12.316004938410892, 8.100000000000001, 11.906154983827506),
            Block.box(4.846782397407008, 2.65, 3.7476482239059, 11.646782397407009, 2.9499999999999997, 11.947648223905901),
            Block.box(4.746782397407008, 2.45, 3.6476482239059003, 4.846782397407008, 3.1500000000000004, 12.1476482239059),
            Block.box(4.846782397407008, 2.9499999999999997, 3.6476482239059003, 11.846782397407008, 3.15, 12.1476482239059),
            Block.box(4.846782397407008, 2.4499999999999997, 3.6476482239059003, 11.846782397407008, 2.7, 12.1476482239059),
            Block.box(4.700000000000006, 11.95, 5.0738484561152575, 11.25, 13.15, 13.27384845611526),
            Block.box(11.25, 11.799999999999999, 4.873848456115258, 11.35, 13.249999999999998, 13.37384845611526),
            Block.box(4.500000000000007, 13.049999999999999, 4.873848456115258, 11.25, 13.249999999999998, 13.37384845611526),
            Block.box(4.500000000000007, 11.8, 4.873848456115258, 11.25, 12, 13.37384845611526),
            Block.box(5.459100685035347, 6.550000000000001, 2.911166545067676, 12.009100685035353, 7.750000000000001, 11.111166545067675),
            Block.box(5.359100685035347, 6.400000000000001, 2.8111665450676764, 5.459100685035347, 7.8500000000000005, 11.311166545067675),
            Block.box(5.459100685035347, 7.65, 2.8111665450676764, 12.209100685035352, 7.8500000000000005, 11.311166545067675),
            Block.box(5.459100685035347, 6.4, 2.8111665450676764, 12.209100685035352, 6.6000000000000005, 11.311166545067675),
            Block.box(4.93318012120276, 1.15, 2.9547135142815026, 11.48318012120276, 2.3499999999999996, 11.154713514281504),
            Block.box(4.83318012120276, 0.9500000000000002, 2.854713514281503, 4.93318012120276, 2.45, 11.354713514281503),
            Block.box(4.93318012120276, 2.25, 2.854713514281503, 11.68318012120276, 2.45, 11.354713514281503),
            Block.box(4.93318012120276, 0.95, 2.854713514281503, 11.68318012120276, 1.2, 11.354713514281503),
            Block.box(4.5276872099556655, 10.950000000000001, 4.514359746433803, 11.327687209955657, 11.65, 13.214359746433802),
            Block.box(11.32768720995566, 10.75, 4.314359746433803, 11.427687209955657, 11.75, 13.314359746433801),
            Block.box(4.327687209955666, 11.55, 4.314359746433803, 11.327687209955657, 11.75, 13.314359746433801),
            Block.box(4.327687209955666, 10.749999999999998, 4.314359746433803, 11.327687209955657, 11, 13.314359746433801),
            Block.box(4.9160049384108895, 5.550000000000002, 3.4561549838275063, 11.71600493841089, 6.250000000000001, 12.156154983827506),
            Block.box(4.816004938410888, 5.350000000000001, 3.3561549838275067, 4.916004938410888, 6.350000000000001, 12.356154983827505),
            Block.box(4.9160049384108895, 6.150000000000001, 3.3561549838275067, 11.91600493841089, 6.350000000000001, 12.356154983827505),
            Block.box(4.9160049384108895, 5.350000000000001, 3.3561549838275067, 11.91600493841089, 5.600000000000001, 12.356154983827505),
            Block.box(4.446782397407009, 0.1499999999999999, 3.697648223905899, 11.24678239740701, 0.8499999999999999, 12.3976482239059),
            Block.box(4.34678239740701, -1.1102230246251565e-16, 3.5976482239058996, 4.446782397407009, 0.95, 12.5976482239059),
            Block.box(4.446782397407009, 0.75, 3.5976482239058996, 11.44678239740701, 0.95, 12.5976482239059),
            Block.box(4.446782397407009, 0, 3.5976482239058996, 11.44678239740701, 0.19999999999999996, 12.5976482239059),
            Block.box(4.677687209955668, 15.549999999999999, 5.564359746433803, 10.727687209955658, 15.850000000000005, 12.764359746433803),
            Block.box(10.727687209955658, 15.450000000000005, 5.364359746433804, 10.82768720995566, 15.999999999999998, 12.864359746433802),
            Block.box(4.477687209955668, 15.850000000000005, 5.364359746433804, 10.727687209955658, 15.999999999999998, 12.864359746433802),
            Block.box(4.477687209955668, 15.45, 5.364359746433804, 10.727687209955658, 15.600000000000003, 12.864359746433802)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    public static final VoxelShape SHAPE_W = Stream.of(
            Block.box(5.04106032682895, 10.250000000000004, 5.4664378516603325, 12.241060326828949, 10.55, 11.516437851660333),
            Block.box(4.84106032682895, 10.100000000000001, 5.366437851660331, 12.341060326828948, 10.750000000000004, 5.466437851660331),
            Block.box(4.84106032682895, 10.550000000000004, 5.4664378516603325, 12.341060326828948, 10.750000000000004, 11.716437851660332),
            Block.box(4.84106032682895, 10.100000000000001, 5.4664378516603325, 12.341060326828948, 10.3, 11.716437851660332),
            Block.box(4.799567086750553, 4.8500000000000005, 4.997215310656452, 11.999567086750556, 5.15, 11.047215310656453),
            Block.box(4.599567086750554, 4.700000000000001, 4.897215310656453, 12.099567086750556, 5.3500000000000005, 4.997215310656452),
            Block.box(4.599567086750554, 5.150000000000001, 4.997215310656452, 12.099567086750556, 5.350000000000001, 11.247215310656452),
            Block.box(4.599567086750554, 4.7, 4.997215310656452, 12.099567086750556, 4.9, 11.247215310656452),
            Block.box(2.491193126327042, 14.150000000000004, 4.666812883781237, 10.691193126327041, 15.350000000000003, 11.216812883781232),
            Block.box(2.3911931263270407, 14.000000000000002, 11.216812883781232, 10.891193126327039, 15.450000000000005, 11.316812883781232),
            Block.box(2.3911931263270407, 15.250000000000004, 4.4668128837812375, 10.891193126327039, 15.450000000000005, 11.216812883781232),
            Block.box(2.3911931263270407, 14.000000000000005, 4.4668128837812375, 10.891193126327039, 14.200000000000006, 11.216812883781232),
            Block.box(4.306075365724846, 8.750000000000004, 4.6835563725359055, 12.506075365724843, 9.95, 11.233556372535906),
            Block.box(4.106075365724848, 8.600000000000001, 4.583556372535906, 12.606075365724845, 10.05, 4.6835563725359055),
            Block.box(4.106075365724848, 9.850000000000001, 4.6835563725359055, 12.606075365724845, 10.05, 11.433556372535906),
            Block.box(4.106075365724848, 8.600000000000001, 4.6835563725359055, 12.606075365724845, 8.8, 11.433556372535906),
            Block.box(3.9034010114421474, 3.350000000000001, 4.442466732715653, 12.103401011442148, 4.549999999999999, 10.992466732715652),
            Block.box(3.70340101144215, 3.200000000000001, 4.342466732715653, 12.20340101144215, 4.65, 4.442466732715653),
            Block.box(3.70340101144215, 4.449999999999999, 4.442466732715653, 12.20340101144215, 4.65, 11.192466732715651),
            Block.box(3.70340101144215, 3.2000000000000015, 4.442466732715653, 12.20340101144215, 3.400000000000001, 11.192466732715651),
            Block.box(2.9828555642226533, 13.450000000000001, 4.078120123205112, 11.182855564222653, 13.750000000000002, 10.878120123205102),
            Block.box(2.8828555642226537, 13.299999999999999, 10.878120123205104, 11.382855564222652, 13.950000000000001, 10.978120123205104),
            Block.box(2.8828555642226537, 13.750000000000002, 3.878120123205113, 11.382855564222652, 13.950000000000001, 10.878120123205102),
            Block.box(2.8828555642226537, 13.299999999999999, 3.878120123205113, 11.382855564222652, 13.500000000000002, 10.878120123205102),
            Block.box(4.441060326828948, 8.05, 5.266437851660333, 12.641060326828947, 8.350000000000001, 12.066437851660336),
            Block.box(4.241060326828949, 7.900000000000001, 5.166437851660332, 12.741060326828947, 8.55, 5.266437851660331),
            Block.box(4.241060326828949, 8.350000000000001, 5.266437851660333, 12.741060326828947, 8.55, 12.266437851660337),
            Block.box(4.241060326828949, 7.900000000000002, 5.266437851660333, 12.741060326828947, 8.100000000000001, 12.266437851660337),
            Block.box(4.1995670867505535, 2.65, 4.797215310656453, 12.399567086750555, 2.9499999999999997, 11.597215310656454),
            Block.box(3.999567086750554, 2.45, 4.697215310656453, 12.499567086750554, 3.1500000000000004, 4.797215310656453),
            Block.box(3.999567086750554, 2.9499999999999997, 4.797215310656453, 12.499567086750554, 3.15, 11.797215310656453),
            Block.box(3.999567086750554, 2.4499999999999997, 4.797215310656453, 12.499567086750554, 2.7, 11.797215310656453),
            Block.box(2.873366854541194, 11.95, 4.6504329132494515, 11.073366854541197, 13.15, 11.200432913249445),
            Block.box(2.7733668545411945, 11.799999999999999, 11.200432913249445, 11.273366854541196, 13.249999999999998, 11.300432913249445),
            Block.box(2.7733668545411945, 13.049999999999999, 4.450432913249452, 11.273366854541196, 13.249999999999998, 11.200432913249445),
            Block.box(2.7733668545411945, 11.8, 4.450432913249452, 11.273366854541196, 12, 11.200432913249445),
            Block.box(5.036048765588779, 6.550000000000001, 5.409533598284792, 13.236048765588778, 7.750000000000001, 11.959533598284798),
            Block.box(4.83604876558878, 6.400000000000001, 5.309533598284792, 13.336048765588778, 7.8500000000000005, 5.409533598284792),
            Block.box(4.83604876558878, 7.65, 5.409533598284792, 13.336048765588778, 7.8500000000000005, 12.159533598284797),
            Block.box(4.83604876558878, 6.4, 5.409533598284792, 13.336048765588778, 6.6000000000000005, 12.159533598284797),
            Block.box(4.992501796374951, 1.15, 4.883613034452205, 13.192501796374952, 2.3499999999999996, 11.433613034452206),
            Block.box(4.792501796374951, 0.9500000000000002, 4.783613034452205, 13.292501796374951, 2.45, 4.883613034452205),
            Block.box(4.792501796374951, 2.25, 4.883613034452205, 13.292501796374951, 2.45, 11.633613034452205),
            Block.box(4.792501796374951, 0.95, 4.883613034452205, 13.292501796374951, 1.2, 11.633613034452205),
            Block.box(2.9328555642226526, 10.950000000000001, 4.478120123205111, 11.632855564222652, 11.65, 11.278120123205102),
            Block.box(2.832855564222653, 10.75, 11.278120123205104, 11.832855564222651, 11.75, 11.378120123205102),
            Block.box(2.832855564222653, 11.55, 4.278120123205111, 11.832855564222651, 11.75, 11.278120123205102),
            Block.box(2.832855564222653, 10.749999999999998, 4.278120123205111, 11.832855564222651, 11, 11.278120123205102),
            Block.box(3.991060326828949, 5.550000000000002, 4.866437851660335, 12.691060326828948, 6.250000000000001, 11.666437851660335),
            Block.box(3.7910603268289496, 5.350000000000001, 4.766437851660333, 12.791060326828948, 6.350000000000001, 4.866437851660333),
            Block.box(3.7910603268289496, 6.150000000000001, 4.866437851660335, 12.791060326828948, 6.350000000000001, 11.866437851660335),
            Block.box(3.7910603268289496, 5.350000000000001, 4.866437851660335, 12.791060326828948, 5.600000000000001, 11.866437851660335),
            Block.box(3.749567086750554, 0.1499999999999999, 4.397215310656454, 12.449567086750555, 0.8499999999999999, 11.197215310656455),
            Block.box(3.549567086750555, -1.1102230246251565e-16, 4.297215310656455, 12.549567086750555, 0.95, 4.397215310656454),
            Block.box(3.549567086750555, 0.75, 4.397215310656454, 12.549567086750555, 0.95, 11.397215310656454),
            Block.box(3.549567086750555, 0, 4.397215310656454, 12.549567086750555, 0.19999999999999996, 11.397215310656454),
            Block.box(3.382855564222652, 15.549999999999999, 4.628120123205113, 10.582855564222651, 15.850000000000005, 10.678120123205103),
            Block.box(3.2828555642226522, 15.450000000000005, 10.678120123205103, 10.78285556422265, 15.999999999999998, 10.778120123205104),
            Block.box(3.2828555642226522, 15.850000000000005, 4.4281201232051135, 10.78285556422265, 15.999999999999998, 10.678120123205103),
            Block.box(3.2828555642226522, 15.45, 4.4281201232051135, 10.78285556422265, 15.600000000000003, 10.678120123205103)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    public BookStackBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HorizontalBlock.FACING);
    }



    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos p_2pos0053_3_, ISelectionContext context) {
        switch (state.getValue(HorizontalBlock.FACING)) {
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(HorizontalBlock.FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(HorizontalBlock.FACING, rotation.rotate(state.getValue(HorizontalBlock.FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(HorizontalBlock.FACING)));
    }
}